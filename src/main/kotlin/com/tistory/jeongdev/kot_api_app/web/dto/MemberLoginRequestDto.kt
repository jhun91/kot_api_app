package com.tistory.jeongdev.kot_api_app.web.dto

import com.tistory.jeongdev.kot_api_app.domain.member.Member

class MemberLoginRequestDto(
        var memberId: String? = null,
        var memberPw: String? = null
)