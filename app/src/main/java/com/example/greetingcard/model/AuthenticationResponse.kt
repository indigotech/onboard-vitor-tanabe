package com.example.greetingcard.model

data class AuthenticationResponse(
    val data: AuthenticationDataResponse
)

data class AuthenticationDataResponse(
    val token: String,
<<<<<<< HEAD
    val userAuthentication: UserAuthentication
)

data class UserAuthentication(
    val name: String,
    val email: String
)
=======
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
>>>>>>> 829ad66 (readjusting project architecture)
