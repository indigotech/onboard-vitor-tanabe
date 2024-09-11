package com.example.greetingcard.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
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
    }

    fun validateAndSetEmailErrors() {
        val errors = mutableListOf<String>()
        val email = emailState.value

        if (email.isEmpty()) {
            errors.add("O campo de e-mail está vazio")
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errors.add("E-mail inválido")
        }
        emailErrorMessages.value = errors
    }

    fun validateAndSetPasswordErrors() {
        val errors = mutableListOf<String>()
        val password = passwordState.value
        val oneLetterAndOneNumberPasswordRegex = Regex("(?=.*[a-zA-Z])(?=.*\\d)")

        if (password.isEmpty()) {
            errors.add("O campo de senha está vazio")
        } else if (password.length < 7) {
            errors.add("A senha deve ter pelo menos 7 caracteres")
        } else if (!oneLetterAndOneNumberPasswordRegex.containsMatchIn(password)) {
            errors.add("A senha deve conter pelo menos uma letra e um número")
        }
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