package com.example.greetingcard.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.greetingcard.model.User
import com.example.greetingcard.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private val userRepository = UserRepository.getInstance()

    private val _isLoading = mutableStateOf(false)
    val isLoading: MutableState<Boolean> get() = _isLoading

//    private val _userList = mutableStateOf<List<User>>(emptyList())
//    val userList: MutableState<List<User>> get() = _userList

    private val _userList = MutableStateFlow<PagingData<User>>(PagingData.empty())
    val userList: StateFlow<PagingData<User>> = _userList

    private val _loadErrorMessages = mutableStateOf(listOf<String>())
    val loadErrorMessages: MutableState<List<String>> get() = _loadErrorMessages

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
