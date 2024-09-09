package com.example.greetingcard.viewModel

<<<<<<< HEAD
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greetingcard.model.UserListItem
import com.example.greetingcard.repository.UserRepository
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private val userRepository = UserRepository.getInstance()
    val isLoading: MutableState<Boolean> get() = mutableStateOf(false)
    val userList: MutableState<List<UserListItem>> get() = mutableStateOf<List<UserListItem>>(emptyList())
    val loadErrorMessages: MutableState<List<String>> get() = mutableStateOf(listOf<String>())
=======
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import com.example.greetingcard.model.User

class UserListViewModel : ViewModel() {
    private val _users = mutableStateOf<List<User>>(emptyList())
    val users: State<List<User>> get() = _users
>>>>>>> 7e715bb (mock user list)

    init {
        loadUsers()
    }

    private fun loadUsers() {
<<<<<<< HEAD
        viewModelScope.launch {
            isLoading.value = true
            try {
                val result = userRepository.loadUsers()

                result.onSuccess { userListResult ->
                    userList.value = userListResult
                }.onFailure {
                    loadErrorMessages.value = listOf("Erro ao carregar usuários")
                }
            } catch (e: Exception) {
                loadErrorMessages.value = listOf("Erro inesperado, tente novamente")
            } finally {
                isLoading.value = false
            }
        }
    }
}
=======
        _users.value = listOf(
            User("João", "joao@email.com"),
            User("Maria", "maria@email.com"),
            User("Pedro", "pedro@email.com")
        )
    }
}
>>>>>>> 7e715bb (mock user list)
