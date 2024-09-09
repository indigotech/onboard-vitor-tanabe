package com.example.greetingcard.viewModel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.greetingcard.model.AuthenticationRequestBody
import com.example.greetingcard.rest.UserAuthenticationRetrofitService
import kotlinx.coroutines.launch
import retrofit2.await


class LoginScreenViewModel : ViewModel() {

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
    }

    fun validateAndSetEmailErrors() {
        val errors = mutableListOf<String>()
        val email = _emailState.value

        if (email.isEmpty()) {
            errors.add("O campo de e-mail está vazio")
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errors.add("E-mail inválido")
        }
        _emailErrorMessages.value = errors
    }

    fun validateAndSetPasswordErrors() {
        val errors = mutableListOf<String>()
        val password = _passwordState.value
        val oneLetterAndOneNumberPasswordRegex = Regex("(?=.*[a-zA-Z])(?=.*\\d)")

        if (password.isEmpty()) {
            errors.add("O campo de senha está vazio")
        } else if (password.length < 7) {
            errors.add("A senha deve ter pelo menos 7 caracteres")
        } else if (!oneLetterAndOneNumberPasswordRegex.containsMatchIn(password)) {
            errors.add("A senha deve conter pelo menos uma letra e um número")
        }
        _passwordErrorMessages.value = errors
    }


    fun authenticateUser(
        navController: NavHostController
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val user = AuthenticationRequestBody(_emailState.value, _passwordState.value)
                UserAuthenticationRetrofitService
                    .userAuthenticationRetrofitService
                    .authenticateUser(user).await()
                navController.navigate("UserListScreen")
            } catch (e: Exception) {
                _authenticationErrorMessages.value = listOf("Usuário ou senha inválidos")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
