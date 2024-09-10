package com.example.greetingcard.model

data class LoadListResponse(
    val data: LoadListDataResponse
)

data class LoadListDataResponse(
<<<<<<< HEAD
<<<<<<< HEAD
    val nodes: List<UserListItem>
)

data class UserListItem(
    val name: String,
    val email: String
=======
    val node: List<User>
>>>>>>> a2e96cb (creating repository to pass token)
=======
    val nodes: List<User>
>>>>>>> 38bd94d (feat:userList)
)