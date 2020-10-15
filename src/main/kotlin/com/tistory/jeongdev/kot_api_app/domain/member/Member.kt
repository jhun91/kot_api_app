package com.tistory.jeongdev.kot_api_app.domain.member

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import javax.persistence.*


@Entity
data class Member(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column
        val memberId: String,

        @Column
        private val memberPw: String,

        @Column
        private val memberName: String,

        @Column
        private val lastLoginTime: LocalDateTime? = null,

        @Column
        private val auth: String? = null

) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val roles: HashSet<GrantedAuthority> = hashSetOf()

        if(auth != null) {
            for (role in auth.split(",")) {
                roles.add(SimpleGrantedAuthority(role))
            }
        }

        return roles
    }

    override fun getPassword(): String {
        return memberPw
    }

    override fun getUsername(): String {
        return memberId
    }

    override fun isAccountNonExpired(): Boolean {
        // 만료되었는지 확인하는 로직
        return true // true -> 만료되지 않았음
    }

    override fun isAccountNonLocked(): Boolean {
        // 계정 잠금되었는지 확인하는 로직
        return true // true -> 잠금되지 않았음
    }

    override fun isCredentialsNonExpired(): Boolean {
        // 패스워드가 만료되었는지 확인하는 로직
        return true // true -> 만료되지 않았음
    }

    override fun isEnabled(): Boolean {
        // 계정이 사용 가능한지 확인하는 로직
        return true // true -> 사용 가능
    }
}