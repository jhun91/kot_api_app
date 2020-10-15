package com.tistory.jeongdev.kot_api_app.config

import com.tistory.jeongdev.kot_api_app.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
@Configuration
class WebMvcConfig(
        val memberService: MemberService
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()
                ?.cors()?.disable()
                ?.httpBasic()?.disable()
                ?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ?.and()
                ?.authorizeRequests()
                ?.antMatchers("/v1/member/**", "/h2")?.permitAll()
                ?.anyRequest()?.authenticated()

        //http?.addFilter....
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder())
    }
}