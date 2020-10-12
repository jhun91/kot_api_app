package com.tistory.jeongdev.kot_api_app.service

import com.tistory.jeongdev.kot_api_app.domain.member.Member
import com.tistory.jeongdev.kot_api_app.domain.member.MemberRepository
import com.tistory.jeongdev.kot_api_app.web.dto.MemberRequestDto
import org.springframework.stereotype.Service

@Service
class MemberService(
        val memberRepository: MemberRepository
) {

    fun save(requestDto: MemberRequestDto): Member {
        return memberRepository.save(requestDto.toEntity())
    }
}