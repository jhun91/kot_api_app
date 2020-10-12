package com.tistory.jeongdev.kot_api_app.domain.member

import java.time.LocalDateTime
import javax.persistence.*


@Entity
data class Member(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column
        val userId: String? = null,

        @Column
        private val userPw: String? = null,

        @Column
        private val name: String? = null,

        @Column
        private val lastLoginTime: LocalDateTime? = null,

        @Column
        private val auth: String? = null
)