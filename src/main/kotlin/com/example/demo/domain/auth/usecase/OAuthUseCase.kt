package com.example.demo.domain.auth.usecase

import com.example.demo.domain.auth.domain.Provider
import com.example.demo.domain.auth.domain.User
import com.example.demo.domain.auth.dto.GoogleOAuthProperties
import com.example.demo.domain.auth.dto.request.GoogleAuthorizeCodeRequest
import com.example.demo.domain.auth.dto.response.GoogleUserInfoResponse
import com.example.demo.domain.auth.exception.WrongAccessTokenException
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
            val objectMapper = ObjectMapper()
            val jsonNode = objectMapper.readTree(body)
            return jsonNode.get("access_token").asText()
        } catch (e: Exception) {
            throw WrongAccessTokenException()
        }
    }

    private fun getAccessToken(code: String, redirectUrl: String): String {
        val tokenUrl: String = "https://oauth2.googleapis.com/token"
        val restTemplate = RestTemplate()

        val headers: HttpHeaders = HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED)

        val params = LinkedMultiValueMap<String, String>()
        params.add("code", code)
        params.add("client_id", googleOAuthProperties.clientId)
        params.add("client_secret", googleOAuthProperties.clientSecret)
        params.add("redirect_uri", redirectUrl)
        params.add("grant_type", "authorization_code")

        val request = HttpEntity(params, headers)
        val response: ResponseEntity<String> =
            restTemplate.exchange(tokenUrl, HttpMethod.POST, request, String::class.java)

        return extractAccessToken(response.body)
    }

    private fun getUserInfo(accessToken: String): GoogleUserInfoResponse? {
        val userInfoUrl: String = "https://www.googleapis.com/oauth2/v2/userinfo"

        val restTemplate = RestTemplate()

        val headers = HttpHeaders()
        headers.setBearerAuth(accessToken)

        val request = HttpEntity<Void>(headers)

        val response = restTemplate.exchange(
            userInfoUrl,
            HttpMethod.GET,
            request,
            GoogleUserInfoResponse::class.java)

        return response.body;
    }

    fun execute(request: GoogleAuthorizeCodeRequest): User {
        val accessToken = getAccessToken(request.code, request.redirectUri)
        val googleUser = getUserInfo(accessToken)
            ?: throw WrongAccessTokenException()

        return userRepository.findByUserEmail(googleUser.email)
            ?: userRepository.save(
                User(
                    userEmail = googleUser.email,
                    username = googleUser.name,
                    userPassword = "",
                    userProvider = Provider.GOOGLE
                )
            )
    }
}