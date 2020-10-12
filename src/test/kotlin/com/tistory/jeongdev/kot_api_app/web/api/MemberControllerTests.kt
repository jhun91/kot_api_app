package com.tistory.jeongdev.kot_api_app.web.api

import com.tistory.jeongdev.kot_api_app.web.dto.MemberRequestDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class MemberControllerTests {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @LocalServerPort
    private var port: Int = 0

    @Test
    fun joinMember() {
        val url = "http://localhost:$port/v1/member/join"

        val userId = "user@test.com"

        val requestDto = MemberRequestDto(
                userId = "user@test.com"
        )

        val responseEntity: ResponseEntity<String> =  restTemplate.postForEntity(url, requestDto, String)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).contains(userId)
    }
}