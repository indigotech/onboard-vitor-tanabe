package com.example.greetingcard.model

data class LoadListResponse(
    val data: LoadListDataResponse
)

data class LoadListDataResponse(
    val nodes: List<ListUserItem>
)

data class ListUserItem(
    val id: String,
    val name: String,
    val email: String
)