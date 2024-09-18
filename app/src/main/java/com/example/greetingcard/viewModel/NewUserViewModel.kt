package com.example.greetingcard.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greetingcard.model.NewUserRequest
import com.example.greetingcard.model.Roles
import com.example.greetingcard.repository.UserRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class NewUserViewModel : ViewModel() {

    private val userRepository = UserRepository.getInstance()
    val nameState = mutableStateOf("")
    val emailState = mutableStateOf("")
    val phoneState = mutableStateOf("")

    @RequiresApi(Build.VERSION_CODES.O)
    val birthDateState = mutableStateOf(LocalDate.now())
    val passwordState = mutableStateOf("")
    val roleState = mutableStateOf(Roles.NOROLE)

    val nameErrorMessages = mutableStateOf(listOf<String>())
    val emailErrorMessages = mutableStateOf(listOf<String>())
    val phoneErrorMessages = mutableStateOf(listOf<String>())
    val birthDateErrorMessages = mutableStateOf(listOf<String>())
    val passwordErrorMessages = mutableStateOf(listOf<String>())
    val roleErrorMessages = mutableStateOf(listOf<String>())
    val addNewUserErrorMessages = mutableStateOf(listOf<String>())

    val isLoading= mutableStateOf(false)

    fun updateNameInput(name: String) {
        nameState.value = name
    }

    fun updateEmailInput(email: String) {
        emailState.value = email
    }

    fun updatePhoneInput(phone: String) {
        phoneState.value = phone
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateBirthDateInput(birthDate: LocalDate) {
        birthDateState.value = birthDate
    }

    fun updatePasswordInput(password: String) {
        passwordState.value = password
    }

    fun updateRoleInput(role: Roles) {
        roleState.value = role
    }

    fun validateAndSetNameErrors() {
        val errors = mutableListOf<String>()
        val twoWordsRegex = nameState.value.trim().split("\\s+".toRegex())

        if (twoWordsRegex.size < 2) {
            errors.add("Nome deve ser completo")
        }
        nameErrorMessages.value = errors
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

    fun validateAndSetPhoneErrors() {
        val errors = mutableListOf<String>()

        val phone = phoneState.value

        if (phone.isEmpty()) {
            errors.add("O campo de senha está vazio")
        } else if (phone.length < 10 || phone.length > 11) {
            errors.add("O número deve ter entre 10-11 dígitos")
        } else if (!phone.all { it.isDigit() }) {
            errors.add("O número deve conter apenas dígitos")
        }

        phoneErrorMessages.value = errors
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun validateAndSetBirthDateErrors() {
        val errors = mutableListOf<String>()
        val birthDate = birthDateState.value

        if (birthDate.isAfter(LocalDate.now())) {
            errors.add("A data de nascimento não pode ser no futuro")
        }
        birthDateErrorMessages.value = errors
    }

    fun validateAndSetRoleErrors() {

        val errors = mutableListOf<String>()

        if(roleState.value == Roles.NOROLE) {
            errors.add("Necessário selecionar um cargo")
        }

        roleErrorMessages.value = errors
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun validateAndSetAllErrors() {
        validateAndSetNameErrors()
        validateAndSetEmailErrors()
        validateAndSetPasswordErrors()
        validateAndSetPhoneErrors()
        validateAndSetRoleErrors()
        validateAndSetBirthDateErrors()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addNewUser() {
        val newUser = NewUserRequest(
            name = nameState.value,
            email = emailState.value,
            phone = phoneState.value,
            password = passwordState.value,
            role = Roles.USER,
            birthDate = birthDateState.value.toString()
        )

        viewModelScope.launch {
            isLoading.value = true
            try {
                userRepository.newUser(newUser)
            } catch (e: Exception) {
                addNewUserErrorMessages.value = listOf("Erro ao cadastrar usuário")
            } finally {
                isLoading.value = false
            }
        }
    }
}