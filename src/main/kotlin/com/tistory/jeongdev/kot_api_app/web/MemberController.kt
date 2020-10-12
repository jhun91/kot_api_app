package com.tistory.jeongdev.kot_api_app.web

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/member")
class MemberController {

    @PostMapping("/join")
    fun joinMember(): String {
        return "join"
    }
}