package com.example.greetingcard.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import com.example.greetingcard.model.User

class UserListViewModel : ViewModel() {
    private val _users = mutableStateOf<List<User>>(emptyList())
    val users: State<List<User>> get() = _users

    init {
        loadUsers()
    }

    private fun loadUsers() {
        _users.value = listOf(
            User("João", "joao@email.com"),
            User("Maria", "maria@email.com"),
            User("Pedro", "pedro@email.com")
        )
    }
}