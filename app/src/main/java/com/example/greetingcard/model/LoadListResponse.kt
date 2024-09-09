package com.example.greetingcard.model

data class LoadListResponse(
    val data: LoadListDataResponse
)

data class LoadListDataResponse(
<<<<<<< HEAD
    val nodes: List<UserListItem>
)

data class UserListItem(
    val name: String,
    val email: String
=======
    val node: List<User>
>>>>>>> a2e96cb (creating repository to pass token)
)