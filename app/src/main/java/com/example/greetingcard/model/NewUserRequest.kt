package com.example.greetingcard.model

data class NewUserRequest(
    val name: String,
    val email:String,
    val phone: String,
    val birthDate: String,
    val password: String,
    val role: Roles
    )