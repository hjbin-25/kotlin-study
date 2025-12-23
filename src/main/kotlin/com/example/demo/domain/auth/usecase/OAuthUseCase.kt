package com.example.demo.domain.auth.usecase

import com.example.demo.domain.auth.dto.GoogleOAuthProperties
import com.example.demo.domain.auth.dto.request.GoogleAuthorizeCodeRequest
import com.example.demo.domain.auth.repository.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

@Service
class OAuthUseCase(
    private val userRepository: UserRepository,
    private val googleOAuthProperties: GoogleOAuthProperties
) {
    private fun extractAccessToken(body: String?): String {
        try {
            var objectMapper = ObjectMapper()
            var jsonNode = objectMapper.readTree(body)
            return jsonNode.get("access_token").asText()
        } catch (e: Exception) {
            throw WrongThreadException()
        }
    }

    private fun getAccessToken(code: String, redirectUrl: String): String {
        var tokenUrl: String = "https://oauth2.googleapis.com/token"
        var restTemplate = RestTemplate()

        var headers: HttpHeaders = HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED)

        val params = LinkedMultiValueMap<String, String>()
        params.add("code", code)
        params.add("client_id", googleOAuthProperties.clientId)
        params.add("client_secret", googleOAuthProperties.clientSecret)
        params.add("redirect_uri", redirectUrl)
        params.add("grant_type", "authorization_code")

        var request = HttpEntity(params, headers)
        var response: ResponseEntity<String> =
            restTemplate.exchange(tokenUrl, HttpMethod.POST, request, String::class.java)

        return extractAccessToken(response.body)
    }
}