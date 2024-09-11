package com.example.greetingcard.model

data class LoadListResponse(
    val data: LoadListDataResponse
)

data class LoadListDataResponse(
    val nodes: List<UserListItem>
)

data class UserListItem(
    val name: String,
    val email: String
)