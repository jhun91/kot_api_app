package com.tistory.jeongdev.kot_api_app.web.dto

import com.tistory.jeongdev.kot_api_app.domain.member.Member

class MemberRequestDto(
        var memberId: String? = null,
        var memberPw: String? = null,
        var memberName: String? = null
) {
    fun toEntity(): Member {
        return Member(
            memberId = memberId,
            memberPw = memberPw,
            memberName = memberName
        )
    }
}