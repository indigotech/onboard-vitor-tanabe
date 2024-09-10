package com.example.greetingcard.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.greetingcard.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val userRepository = UserRepository.getInstance()
    val emailState: MutableState<String> get() = mutableStateOf("")
    val passwordState: MutableState<String> get() = mutableStateOf("")
    val emailErrorMessages: MutableState<List<String>> get() = mutableStateOf(listOf<String>())
    val passwordErrorMessages: MutableState<List<String>> get() = mutableStateOf(listOf<String>())
    val authenticationErrorMessages: MutableState<List<String>> get() = mutableStateOf(listOf<String>())
    val isLoading: MutableState<Boolean> get() = mutableStateOf(false)

    fun updateEmailInput(email: String) {
        emailState.value = email
    }

    fun updatePasswordInput(password: String) {
        passwordState.value = password
=======
import com.example.greetingcard.model.AuthenticationRequestBody
=======
>>>>>>> b2ba40f (list comming from repository)
import com.example.greetingcard.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val userRepository = UserRepository.getInstance()

    private val _emailState = mutableStateOf("")
    val emailState: MutableState<String> get() = _emailState

    private val _passwordState = mutableStateOf("")
    val passwordState: MutableState<String> get() = _passwordState

    private val _emailErrorMessages = mutableStateOf(listOf<String>())
    val emailErrorMessages: MutableState<List<String>> get() = _emailErrorMessages

    private val _passwordErrorMessages = mutableStateOf(listOf<String>())
    val passwordErrorMessages: MutableState<List<String>> get() = _passwordErrorMessages

    private val _authenticationErrorMessages = mutableStateOf(listOf<String>())
    val authenticationErrorMessages: MutableState<List<String>> get() = _authenticationErrorMessages

    private val _isLoading = mutableStateOf(false)
    val isLoading: MutableState<Boolean> get() = _isLoading

    fun updateEmailInput(email: String) {
        _emailState.value = email
    }

    fun updatePasswordInput(password: String) {
        _passwordState.value = password
>>>>>>> 7e715bb (mock user list)
    }

    fun validateAndSetEmailErrors() {
        val errors = mutableListOf<String>()
<<<<<<< HEAD
        val email = emailState.value
=======
        val email = _emailState.value
>>>>>>> 7e715bb (mock user list)

        if (email.isEmpty()) {
            errors.add("O campo de e-mail está vazio")
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errors.add("E-mail inválido")
        }
<<<<<<< HEAD
        emailErrorMessages.value = errors
=======
        _emailErrorMessages.value = errors
>>>>>>> 7e715bb (mock user list)
    }

    fun validateAndSetPasswordErrors() {
        val errors = mutableListOf<String>()
<<<<<<< HEAD
        val password = passwordState.value
=======
        val password = _passwordState.value
>>>>>>> 7e715bb (mock user list)
        val oneLetterAndOneNumberPasswordRegex = Regex("(?=.*[a-zA-Z])(?=.*\\d)")

        if (password.isEmpty()) {
            errors.add("O campo de senha está vazio")
        } else if (password.length < 7) {
            errors.add("A senha deve ter pelo menos 7 caracteres")
        } else if (!oneLetterAndOneNumberPasswordRegex.containsMatchIn(password)) {
            errors.add("A senha deve conter pelo menos uma letra e um número")
        }
<<<<<<< HEAD
        passwordErrorMessages.value = errors
    }


    fun authenticateUser(navController: NavHostController) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val result = userRepository.authenticateUser(emailState.value, passwordState.value)
                result.onSuccess { token ->
                    navController.navigate("UserListScreen")
                }.onFailure {
                    authenticationErrorMessages.value = listOf("Usuário ou senha inválidos")
                }
            } catch (e: Exception) {
                authenticationErrorMessages.value = listOf("Erro inesperado, tente novamente")
            } finally {
                isLoading.value = false
            }
        }
    }
}
=======
        _passwordErrorMessages.value = errors
    }


    fun authenticateUser(navController: NavHostController) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = userRepository.authenticateUser(_emailState.value, _passwordState.value)
                result.onSuccess { token ->
                    navController.navigate("UserListScreen")
                }.onFailure {
                    _authenticationErrorMessages.value = listOf("Usuário ou senha inválidos")
                }
            } catch (e: Exception) {
                _authenticationErrorMessages.value = listOf("Erro inesperado, tente novamente")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
>>>>>>> 7e715bb (mock user list)
