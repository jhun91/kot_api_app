package com.tistory.jeongdev.kot_api_app.util

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.security.Key
import java.security.SignatureException
import javax.annotation.PostConstruct

@Component
class JwtUtil(
        val secret: String = "jwtsecretkey1jwtsecretkey1jwtsecretkey1123",
        var key: Key? = null
) {

    private val logger = KotlinLogging.logger {}

    @PostConstruct
    fun init() {
        this.key = Keys.hmacShaKeyFor(secret.toByteArray())
    }

    fun createToken(userId: String, name: String): String {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("userName", name)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact()
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build().parseClaimsJws(token)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: {}", e.message);
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message);
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.message);
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message);
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message);
        }
        return false
    }

    fun getUserIdFromToken(token: String): Any? {
        return Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token).body["userId"] to String
    }
}