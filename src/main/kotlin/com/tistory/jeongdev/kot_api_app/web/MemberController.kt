package com.tistory.jeongdev.kot_api_app.web

import com.tistory.jeongdev.kot_api_app.domain.member.Member
import com.tistory.jeongdev.kot_api_app.service.MemberService
import com.tistory.jeongdev.kot_api_app.util.JwtUtil
import com.tistory.jeongdev.kot_api_app.web.dto.MemberJoinRequestDto
import com.tistory.jeongdev.kot_api_app.web.dto.MemberLoginRequestDto
import com.tistory.jeongdev.kot_api_app.web.dto.MemberLoginResponseDto
import com.tistory.jeongdev.kot_api_app.web.dto.MsgResponseDto
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/member")
class MemberController(
        val memberService: MemberService,
        val authenticationManager: AuthenticationManager,
        val jwtUtil: JwtUtil
) {

    @PostMapping("/join")
    fun joinMember(@RequestBody joinRequestDto: MemberJoinRequestDto): ResponseEntity<MsgResponseDto> {
        val newMember = memberService.save(joinRequestDto)

        return ResponseEntity.ok(MsgResponseDto("${newMember.memberId} 계정이 생성되었습니다."))
    }

    @PostMapping("/login")
    fun loginMember(@RequestBody requestDto: MemberLoginRequestDto): ResponseEntity<MemberLoginResponseDto> {
        val loginMember: Member = memberService.loadUserByUsername(requestDto.memberId) as Member
        val authentication: Authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginMember, requestDto.memberPw)
        )
        SecurityContextHolder.getContext().authentication = authentication

        val token: String = jwtUtil.createToken(loginMember.memberId, loginMember.memberName)

        val headers: HttpHeaders = HttpHeaders()
        headers.set("authorization", "Bearer $token")

        return ResponseEntity(
                MemberLoginResponseDto(
                        accessToken = token
                ),
                headers,
                HttpStatus.OK
        )
    }
}