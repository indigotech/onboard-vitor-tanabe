package com.example.greetingcard.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.greetingcard.repository.UserRepository

class NewUserViewModel : ViewModel() {

    private val userRepository = UserRepository.getInstance()

    private val _nameState = mutableStateOf("")
    val nameState: MutableState<String> get() = _nameState

    private val _emailState = mutableStateOf("")
    val emailState: MutableState<String> get() = _emailState

    private val _phoneState = mutableStateOf("")
    val phoneState: MutableState<String> get() = _phoneState

    private val _birthDateState = mutableStateOf("")
    val birthDateState: MutableState<String> get() = _birthDateState

    private val _passwordState = mutableStateOf("")
    val passwordState: MutableState<String> get() = _passwordState

    private val _roleState = mutableStateOf("")
    val roleState: MutableState<String> get() = _roleState


    fun updateNameInput(name: String) {
        _nameState.value = name
    }
    fun updateEmailInput(email: String) {
        _emailState.value = email
    }
    fun updatePhoneInput(phone: String) {
        _phoneState.value = phone
    }
    fun updateBirthDateInput(birthDate: String) {
        _birthDateState.value = birthDate
    }
    fun updatePasswordInput(password: String) {
        _passwordState.value = password
    }
    fun updateRoleInput(role: String) {
        _roleState.value = role
    }
}