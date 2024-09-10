package com.example.greetingcard.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
<<<<<<< HEAD
import com.example.greetingcard.model.UserListItem
=======
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.greetingcard.model.User
>>>>>>> 986e103 (pagination)
import com.example.greetingcard.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private val userRepository = UserRepository.getInstance()
    val isLoading: MutableState<Boolean> get() = mutableStateOf(false)
    val userList: MutableState<List<UserListItem>> get() = mutableStateOf<List<UserListItem>>(emptyList())
    val loadErrorMessages: MutableState<List<String>> get() = mutableStateOf(listOf<String>())

    init {
        loadUsers()
    }


    fun loadUsers() {
        viewModelScope.launch {
            userRepository.loadUsers()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _userList.value = pagingData
                }
        }
    }

//    private fun loadUsers() {
//        viewModelScope.launch {
//            _isLoading.value = true
//            try {
//                val result = userRepository.loadUsers()
//
//                result.onSuccess { userListResult ->
//                    _userList.value = userListResult
//                }.onFailure {
//                    _loadErrorMessages.value = listOf("Erro ao carregar usuários")
//                }
//            } catch (e: Exception) {
//                _loadErrorMessages.value = listOf("Erro inesperado, tente novamente")
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
}
