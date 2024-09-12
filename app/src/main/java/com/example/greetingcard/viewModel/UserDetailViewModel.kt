package com.example.greetingcard.viewModel

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greetingcard.model.Roles
import com.example.greetingcard.repository.UserRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class UserDetailViewModel() : ViewModel() {

    private val userRepository = UserRepository.getInstance()
    val nameState = mutableStateOf("")
    val emailState = mutableStateOf("")
    val phoneState = mutableStateOf("")
    @RequiresApi(Build.VERSION_CODES.O)
    val birthDateState = mutableStateOf("")
    val roleState = mutableStateOf(Roles.NOROLE)
    val isLoading = mutableStateOf(false)

    val loadUserDetailErrorMessages = mutableStateOf(listOf<String>())

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadUserDetail(userIdState: String) {

        viewModelScope.launch {
            isLoading.value = true
            try {
                val user = userRepository.loadUserDetail(userIdState)
                nameState.value = user.data.name
                emailState.value = user.data.email
                phoneState.value = user.data.phone
                birthDateState.value = formatDate(user.data.birthDate)
                if(user.data.role !in Roles.values()) {
                    roleState.value = Roles.NOROLE
                } else {
                    roleState.value = user.data.role
                }

            } catch (e: Exception) {
                loadUserDetailErrorMessages.value = listOf("Erro ao carregar usuário")
            } finally {
                isLoading.value = false
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatDate(dateString: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        return try {
            val date = LocalDate.parse(dateString, inputFormatter)
            date.format(outputFormatter)
        } catch (e: DateTimeParseException) {
            "Data inválida"
        }
    }

}