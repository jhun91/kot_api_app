package com.tistory.jeongdev.kot_api_app.web

import com.tistory.jeongdev.kot_api_app.service.MemberService
import com.tistory.jeongdev.kot_api_app.web.dto.MemberRequestDto
import com.tistory.jeongdev.kot_api_app.web.dto.MsgResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/member")
class MemberController(
        val memberService: MemberService
) {

    @PostMapping("/join")
    fun joinMember(@RequestBody requestDto: MemberRequestDto): ResponseEntity<MsgResponseDto> {
        val newMember = memberService.save(requestDto)

        return ResponseEntity.ok(MsgResponseDto("${newMember.userId} 계정이 생성되었습니다."))
    }
}