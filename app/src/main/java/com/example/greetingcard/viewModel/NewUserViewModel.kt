package com.example.greetingcard.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greetingcard.model.NewUserRequest
import com.example.greetingcard.model.Roles
import com.example.greetingcard.repository.UserRepository
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class NewUserViewModel : ViewModel() {

    private val userRepository = UserRepository.getInstance()
    var nameState by mutableStateOf("")
    var emailState by mutableStateOf("")
    var phoneState by mutableStateOf("")
    var birthDateState by mutableStateOf("")
    var passwordState by mutableStateOf("")
    var roleState by mutableStateOf<Roles?>(null)

    var nameErrorMessages by mutableStateOf(listOf<String>())
    var emailErrorMessages by mutableStateOf(listOf<String>())
    var phoneErrorMessages by mutableStateOf(listOf<String>())
    var birthDateErrorMessages by mutableStateOf(listOf<String>())
    var passwordErrorMessages by mutableStateOf(listOf<String>())
    var roleErrorMessages by mutableStateOf(listOf<String>())
    var addNewUserErrorMessages by mutableStateOf(listOf<String>())

    var isLoading by mutableStateOf(false)

    fun updateNameInput(name: String) {
        nameState = name
    }

    fun updateEmailInput(email: String) {
        emailState = email
    }

    fun updatePhoneInput(phone: String) {
        phoneState = phone
    }

    fun updatePasswordInput(password: String) {
        passwordState = password
    }

    fun updateBirthDateInput(birthDate: String) {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        try {
            val localDate = LocalDate.parse(birthDate, inputFormatter)
            val formattedDate = localDate.format(outputFormatter)
            birthDateState = formattedDate
        } catch (e: DateTimeParseException) {
            birthDateState = "Data inválida"
        }
    }

    fun updateRoleInput(role: Roles) {
        roleState = role
    }

    private fun validateAndSetNameErrors() {
        val errors = mutableListOf<String>()
        val twoWordsRegex = nameState.trim().split("\\s+".toRegex())

        if (twoWordsRegex.size < 2) {
            errors.add("Nome deve ser completo")
        }
        nameErrorMessages = errors
    }

    private fun validateAndSetEmailErrors() {
        val errors = mutableListOf<String>()
        val email = emailState
        if (email.isEmpty()) {
            errors.add("O campo de e-mail está vazio")
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errors.add("E-mail inválido")
        }
        emailErrorMessages = errors
    }

    private fun validateAndSetPhoneErrors() {
        val errors = mutableListOf<String>()
        val phone = phoneState
        if (phone.isEmpty()) {
            errors.add("O campo de senha está vazio")
        } else if (phone.length < 10 || phone.length > 11) {
            errors.add("O número deve ter entre 10-11 dígitos")
        } else if (!phone.all { it.isDigit() }) {
            errors.add("O número deve conter apenas dígitos")
        }
        phoneErrorMessages = errors
    }

    private fun validateAndSetPasswordErrors() {
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

    private fun validateAndSetRoleErrors() {

        val errors = mutableListOf<String>()

        if (roleState != Roles.ADMIN || roleState != Roles.USER) {
            errors.add("Necessário selecionar um cargo")
        }

        roleErrorMessages = errors
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun validateAndSetAllErrors() {
        validateAndSetNameErrors()
        validateAndSetEmailErrors()
        validateAndSetPasswordErrors()
        validateAndSetPhoneErrors()
        validateAndSetRoleErrors()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addNewUser() {
        val newUser = NewUserRequest(
            name = nameState,
            email = emailState,
            phone = phoneState,
            password = passwordState,
            role = Roles.USER,
            birthDate = birthDateState.toString()
        )

        viewModelScope.launch {
            isLoading = true
            try {
                userRepository.newUser(newUser)
            } catch (e: Exception) {
                addNewUserErrorMessages = listOf("Erro ao cadastrar usuário")
            } finally {
                isLoading = false
            }
        }
    }
}