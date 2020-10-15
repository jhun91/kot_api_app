package com.tistory.jeongdev.kot_api_app.service

import com.tistory.jeongdev.kot_api_app.domain.member.Member
import com.tistory.jeongdev.kot_api_app.domain.member.MemberRepository
import com.tistory.jeongdev.kot_api_app.web.dto.MemberJoinRequestDto
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
        val memberRepository: MemberRepository
) : UserDetailsService {

    fun save(joinRequestDto: MemberJoinRequestDto): Member {

        val encoder = BCryptPasswordEncoder()

        val memberPw = joinRequestDto.memberPw

        joinRequestDto.memberPw = encoder.encode(memberPw)
        joinRequestDto.auth = "ROLE_USER"

        return memberRepository.save(joinRequestDto.toEntity())
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        return memberRepository.findByMemberId(username).orElseThrow { UsernameNotFoundException(username) }
    }
}