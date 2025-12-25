package com.example.demo.domain.auth.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

class JwtFilter(
    private val jwtAuth: JwtAuthenticationFilter,
    private val pathMatcher: AntPathMatcher = AntPathMatcher(),
): OncePerRequestFilter() {
    val PERMIT_ALL_PATHS = mutableListOf<String?>(
        "/",
        "/find/all/posts"
    )

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val path = request.requestURI

        // 허용 경로면 그냥 통과
        if (isPermitAllPath(path)) {
            filterChain.doFilter(request, response)
            return
        }

        // 나머지 경로는 JWT 필수
        val authHeader = request.getHeader("Authorization")

        if (!authHeader.isNullOrBlank() && authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring(7).trim()

            try {
                jwtAuth.validateToken(token)
                val userEmail = jwtAuth.userEmailFromToken(token)

                val authentication = UsernamePasswordAuthenticationToken(
                    userEmail, null, emptyList()
                )
                SecurityContextHolder.getContext().authentication = authentication

            } catch (e: Exception) {
                sendErrorResponse(response, "Invalid JWT token", "잘못된 토큰")
                return
            }
        } else {
            sendErrorResponse(response, "Unauthorized", "JWT token is required")
            return
        }

        filterChain.doFilter(request, response)
    }

    fun isPermitAllPath(path: String): Boolean {
        return PERMIT_ALL_PATHS.any { pattern -> pathMatcher.match(pattern, path) }
    }

    fun sendErrorResponse(response: HttpServletResponse, error: String, message: String) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = "application/json;charset=UTF-8"
        response.writer.write("""{"error": "$error", "message": "$message"}""")
    }
}