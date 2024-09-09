package com.example.greetingcard.model

data class AuthenticationResponse(
    val data: AuthenticationDataResponse
)

data class AuthenticationDataResponse(
    val token: String,
    val user: User
)