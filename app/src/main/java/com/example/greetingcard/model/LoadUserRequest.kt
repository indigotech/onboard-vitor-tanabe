package com.example.greetingcard.model

data class LoadUserResponse(
    val data: UserDetail
)

data class UserDetail(
    val id: String,
    val name: String,
    val email: String,
    val birthDate: String,
    val phone: String,
    val role: Roles
)