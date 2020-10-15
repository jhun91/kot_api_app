package com.tistory.jeongdev.kot_api_app.domain.member

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByMemberId(username: String?): Optional<Member>
}