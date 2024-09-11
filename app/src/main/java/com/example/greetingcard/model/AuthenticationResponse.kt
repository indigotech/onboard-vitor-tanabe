package com.example.greetingcard.model

data class AuthenticationResponse(
    val data: AuthenticationDataResponse
)

data class AuthenticationDataResponse(
    val token: String,
    val userAuthentication: UserAuthentication
)

data class UserAuthentication(
    val name: String,
    val email: String
)