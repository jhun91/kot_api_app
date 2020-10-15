package com.tistory.jeongdev.kot_api_app.web.dto

import com.tistory.jeongdev.kot_api_app.domain.member.Member

class MemberJoinRequestDto(
        var memberId: String,
        var memberPw: String,
        var memberName: String,
        var auth: String
) {
    fun toEntity(): Member {
        return Member(
                memberId = memberId,
                memberPw = memberPw,
                memberName = memberName,
                auth = auth
        )
    }
}