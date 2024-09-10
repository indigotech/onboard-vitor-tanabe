package com.example.greetingcard.viewModel

<<<<<<< HEAD
<<<<<<< HEAD
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
<<<<<<< HEAD
import com.example.greetingcard.model.UserListItem
import com.example.greetingcard.repository.UserRepository
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private val userRepository = UserRepository.getInstance()
    val isLoading: MutableState<Boolean> get() = mutableStateOf(false)
    val userList: MutableState<List<UserListItem>> get() = mutableStateOf<List<UserListItem>>(emptyList())
    val loadErrorMessages: MutableState<List<String>> get() = mutableStateOf(listOf<String>())
=======
=======
import androidx.compose.runtime.MutableState
>>>>>>> b2ba40f (list comming from repository)
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
=======
import androidx.paging.PagingData
import androidx.paging.cachedIn
>>>>>>> 986e103 (pagination)
import com.example.greetingcard.model.User
import com.example.greetingcard.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

<<<<<<< HEAD
<<<<<<< HEAD
class UserListViewModel : ViewModel() {
    private val _users = mutableStateOf<List<User>>(emptyList())
    val users: State<List<User>> get() = _users
>>>>>>> 7e715bb (mock user list)
=======
class UserListViewModel() : ViewModel() {
=======
class UserListViewModel : ViewModel() {
>>>>>>> 38bd94d (feat:userList)

    private val userRepository = UserRepository.getInstance()

    private val _isLoading = mutableStateOf(false)
    val isLoading: MutableState<Boolean> get() = _isLoading

    private val _userList = MutableStateFlow<PagingData<User>>(PagingData.empty())
    val userList: StateFlow<PagingData<User>> = _userList

    private val _loadErrorMessages = mutableStateOf(listOf<String>())
    val loadErrorMessages: MutableState<List<String>> get() = _loadErrorMessages
>>>>>>> b2ba40f (list comming from repository)

    init {
        loadUsers()
    }

<<<<<<< HEAD
<<<<<<< HEAD
    private fun loadUsers() {
<<<<<<< HEAD
<<<<<<< HEAD
        viewModelScope.launch {
            isLoading.value = true
=======
        viewModelScope.launch {
            _isLoading.value = true
>>>>>>> b2ba40f (list comming from repository)
            try {
                val result = userRepository.loadUsers()

                result.onSuccess { userListResult ->
<<<<<<< HEAD
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
=======
                    _userList.value = userListResult
                }.onFailure {
                    _loadErrorMessages.value = listOf("Erro ao carregar usuários")
=======

=======
>>>>>>> 05807af (removing extra lines and comentaries)
    fun loadUsers() {
        viewModelScope.launch {
            userRepository.loadUsers()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _userList.value = pagingData
>>>>>>> 986e103 (pagination)
                }
        }
    }
}
>>>>>>> b2ba40f (list comming from repository)
