package com.example.greetingcard.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.greetingcard.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val userRepository = UserRepository.getInstance()
    var emailState by mutableStateOf("")
    var passwordState by mutableStateOf("")
    var emailErrorMessages by mutableStateOf(listOf<String>())
    var passwordErrorMessages by mutableStateOf(listOf<String>())
    var authenticationErrorMessages by mutableStateOf(listOf<String>())
    var isLoading by mutableStateOf(false)

    fun updateEmailInput(email: String) {
        emailState = email
    }

    fun updatePasswordInput(password: String) {
        passwordState = password
    }

    fun validateAndSetEmailErrors() {
        val errors = mutableListOf<String>()
        val email = emailState

        if (email.isEmpty()) {
            errors.add("O campo de e-mail está vazio")
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errors.add("E-mail inválido")
        }
        emailErrorMessages = errors
    }

    fun validateAndSetPasswordErrors() {
        val errors = mutableListOf<String>()
        val password = passwordState
        val oneLetterAndOneNumberPasswordRegex = Regex("(?=.*[a-zA-Z])(?=.*\\d)")

        if (password.isEmpty()) {
            errors.add("O campo de senha está vazio")
        } else if (password.length < 7) {
            errors.add("A senha deve ter pelo menos 7 caracteres")
        } else if (!oneLetterAndOneNumberPasswordRegex.containsMatchIn(password)) {
            errors.add("A senha deve conter pelo menos uma letra e um número")
        }
        passwordErrorMessages = errors
    }


    fun authenticateUser(navController: NavHostController) {
        viewModelScope.launch {
            isLoading = true
            try {
                val result = userRepository.authenticateUser(emailState, passwordState)
                if (result.isSuccess) {
                    navController.navigate("UserListScreen")
                } else {
                    val errors = mutableListOf<String>()
                    errors.add("Email ou senha inválidos")
                    authenticationErrorMessages = errors
                }
            } catch (e: Exception) {
                val errors = mutableListOf<String>()
                errors.add("Erro ao autenticar: ${e.message}")
                authenticationErrorMessages = errors
            } finally {
                isLoading = false
            }
        }
    }
}