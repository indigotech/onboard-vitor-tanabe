package com.example.greetingcard.model

data class AuthenticationResponse(
    val data: AuthenticationDataResponse
)

data class AuthenticationDataResponse(
    val token: String,
    val user: User
)

data class User(
    val id: String,
    val name: String,
    val email: String,
    val birthDate: String,
    val phone: String,
    val role: String
)
