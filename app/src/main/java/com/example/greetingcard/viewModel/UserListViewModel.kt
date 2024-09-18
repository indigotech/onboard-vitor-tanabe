package com.example.greetingcard.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.greetingcard.model.ListUserItem
import com.example.greetingcard.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private val userRepository = UserRepository.getInstance()

    init {
        loadUsers()
    }

    var isLoading by mutableStateOf(false)
    var userList = MutableStateFlow<PagingData<ListUserItem>>(PagingData.empty())
    var loadErrorMessages by mutableStateOf(listOf<String>())

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            userRepository.loadUsers()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    userList.value = pagingData
                }
        }
    }
}