package com.example.greetingcard.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greetingcard.model.Roles
import com.example.greetingcard.model.UserDetailState
import com.example.greetingcard.repository.UserRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class UserDetailViewModel() : ViewModel() {

    private val userRepository = UserRepository.getInstance()
    var userDetailState by mutableStateOf(UserDetailState())


    var isLoading by mutableStateOf(false)

    var loadUserDetailErrorMessages by mutableStateOf(listOf<String>())

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadUserDetail(userIdState: String) {
        viewModelScope.launch {
            isLoading = true
            try {
                val user = userRepository.loadUserDetail(userIdState)
                userDetailState = UserDetailState(
                    name = user.data.name,
                    email = user.data.email,
                    phone = user.data.phone,
                    birthDate = formatDate(user.data.birthDate),
                    role = user.data.role.toString()
                )
            } catch (e: Exception) {
                loadUserDetailErrorMessages = listOf(e.message ?: "Erro ao carregar usuário")
            } finally {
                isLoading = false
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