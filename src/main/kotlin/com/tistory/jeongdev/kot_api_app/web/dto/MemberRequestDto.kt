package com.tistory.jeongdev.kot_api_app.web.dto

import com.tistory.jeongdev.kot_api_app.domain.member.Member

class MemberRequestDto(
        var userId: String? = null
) {
    fun toEntity(): Member {
        return Member(
            userId = userId
        )
    }
}