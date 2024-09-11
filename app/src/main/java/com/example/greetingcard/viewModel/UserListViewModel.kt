package com.example.greetingcard.viewModel

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

    init {
        loadUsers()
    }

    private fun loadUsers() {
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
