package com.example.greetingcard.model

data class LoadListResponse(
    val data: LoadListDataResponse
)

data class LoadListDataResponse(
    val node: List<User>
)