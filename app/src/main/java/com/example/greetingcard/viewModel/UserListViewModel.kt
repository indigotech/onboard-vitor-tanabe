package com.example.greetingcard.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greetingcard.model.User
import com.example.greetingcard.repository.UserRepository
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private val userRepository = UserRepository.getInstance()

    private val _isLoading = mutableStateOf(false)
    val isLoading: MutableState<Boolean> get() = _isLoading

    private val _userList = mutableStateOf<List<User>>(emptyList())
    val userList: MutableState<List<User>> get() = _userList

    private val _loadErrorMessages = mutableStateOf(listOf<String>())
    val loadErrorMessages: MutableState<List<String>> get() = _loadErrorMessages

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = userRepository.loadUsers()

                result.onSuccess { userListResult ->
                    _userList.value = userListResult
                }.onFailure {

                    _loadErrorMessages.value = listOf("Erro ao carregar usuários")
                }
            } catch (e: Exception) {
                _loadErrorMessages.value = listOf("Erro inesperado, tente novamente")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
