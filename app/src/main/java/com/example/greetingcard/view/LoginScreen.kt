<<<<<<< HEAD
<<<<<<< HEAD
package com.example.greetingcard.view

<<<<<<< HEAD
=======
package com.example.greetingcard.screens
=======
package com.example.greetingcard.view
>>>>>>> 003ac35 (refactor:mvvm architecture)

import android.content.Context
import android.widget.Toast
>>>>>>> 5e0a393 (viewmodelscope dependecies)
=======
>>>>>>> 7e715bb (mock user list)
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
<<<<<<< HEAD
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.greetingcard.view.component.InputFields
import com.example.greetingcard.viewModel.LoginViewModel

@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel: LoginViewModel = viewModel()
    val emailState by viewModel.emailState
    val passwordState by viewModel.passwordState
    val emailErrorMessages by viewModel.emailErrorMessages
    val passwordErrorMessages by viewModel.passwordErrorMessages
    val authenticationErrorMessages by viewModel.authenticationErrorMessages
    val isLoading by viewModel.isLoading
=======
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.greetingcard.viewModel.LoginViewModel

@Composable
fun LoginScreen(navController: NavHostController) {
<<<<<<< HEAD
<<<<<<< HEAD

    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val emailErrorMessages = remember { mutableStateOf(listOf<String>()) }
    val passwordErrorMessages = remember { mutableStateOf(listOf<String>()) }
    val authenticationErrorMessages = remember { mutableStateOf(listOf<String>()) }

    val context = LocalContext.current

    var auth by remember { mutableStateOf<AuthenticationResponse?>(null) }
    val coroutineScope = rememberCoroutineScope()

    val token = remember {
        mutableStateOf("")
    }

    val isLoading = remember { mutableStateOf(false) }

    fun changeEmailState(email: String) {
        emailState.value = email
    }

    fun changePasswordState(password: String) {
        passwordState.value = password
    }
>>>>>>> 5e0a393 (viewmodelscope dependecies)
=======
    val viewModel: LoginScreenViewModel = viewModel()
=======
    val viewModel: LoginViewModel = viewModel()
>>>>>>> 7e715bb (mock user list)
    val emailState by viewModel.emailState
    val passwordState by viewModel.passwordState
    val emailErrorMessages by viewModel.emailErrorMessages
    val passwordErrorMessages by viewModel.passwordErrorMessages
    val authenticationErrorMessages by viewModel.authenticationErrorMessages
    val isLoading by viewModel.isLoading
>>>>>>> 003ac35 (refactor:mvvm architecture)

    Scaffold(modifier = Modifier.padding(32.dp)) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
<<<<<<< HEAD
<<<<<<< HEAD
            Spacer(modifier = Modifier.height(24.dp))
            Title()
            Spacer(modifier = Modifier.height(48.dp))
            InputFields(
                emailState,
                "E-mail",
                KeyboardType.Email,
                onValueChange = { email -> viewModel.updateEmailInput(email) }
            )
            ShowErrors(emailErrorMessages)
            Spacer(modifier = Modifier.height(36.dp))
            InputFields(
                passwordState,
                "Senha",
                KeyboardType.Password,
                onValueChange = { password -> viewModel.updatePasswordInput(password) },
                true
            )
            ShowErrors(passwordErrorMessages)
            Spacer(modifier = Modifier.height(36.dp))
            if (!isLoading) {
                LoginButton(onClick = {
                    viewModel.validateAndSetEmailErrors()
                    viewModel.validateAndSetPasswordErrors()
                    if (emailErrorMessages.isEmpty() && passwordErrorMessages.isEmpty()) {
                        viewModel.authenticateUser(navController)
                    }
                })
            }
            if (isLoading) {
=======

=======
>>>>>>> 003ac35 (refactor:mvvm architecture)
            Spacer(modifier = Modifier.height(24.dp))
            Title()
            Spacer(modifier = Modifier.height(48.dp))
            InputFields(
                emailState,
                "E-mail",
                KeyboardType.Email,
                onValueChange = { email -> viewModel.updateEmailInput(email) }
            )
            ShowErrors(emailErrorMessages)
            Spacer(modifier = Modifier.height(36.dp))
            InputFields(
                passwordState,
                "Senha",
                KeyboardType.Password,
                onValueChange = { password -> viewModel.updatePasswordInput(password) },
                true
            )
            ShowErrors(passwordErrorMessages)
            Spacer(modifier = Modifier.height(36.dp))
            if (!isLoading) {
                LoginButton(onClick = {
                    viewModel.validateAndSetEmailErrors()
                    viewModel.validateAndSetPasswordErrors()
                    if (emailErrorMessages.isEmpty() && passwordErrorMessages.isEmpty()) {
                        viewModel.authenticateUser(navController)
                    }
                })
            }
<<<<<<< HEAD

            if (isLoading.value) {
>>>>>>> 5e0a393 (viewmodelscope dependecies)
=======
            if (isLoading) {
>>>>>>> 003ac35 (refactor:mvvm architecture)
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
<<<<<<< HEAD
<<<<<<< HEAD
            ShowErrors(authenticationErrorMessages)
=======


            ShowErrors(authenticationErrorMessages.value)
>>>>>>> 5e0a393 (viewmodelscope dependecies)
=======
            ShowErrors(authenticationErrorMessages)
>>>>>>> 003ac35 (refactor:mvvm architecture)
        }
    }
}

@Composable
<<<<<<< HEAD
private fun Title() {
=======
fun Title() {
>>>>>>> 5e0a393 (viewmodelscope dependecies)
    Text(
        fontWeight = FontWeight.Bold, fontSize = 24.sp, text = "Bem vindo(a) à Taqtile!"
    )
}

<<<<<<< HEAD


@Composable
private fun LoginButton(onClick: () -> Unit) {
=======
@Composable
fun InputFields(
    value: String,
    title: String,
    type: KeyboardType,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    Column() {
        Text(text = title)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = type
            ),
            shape = RoundedCornerShape(16.dp),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )
    }
}

@Composable
fun LoginButton(onClick: () -> Unit) {
>>>>>>> 5e0a393 (viewmodelscope dependecies)
    Button(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Text(fontSize = 20.sp, text = "Entrar")
    }
}

@Composable
<<<<<<< HEAD
private fun ShowErrors(errorMessages: List<String>) {
=======
fun ShowErrors(errorMessages: List<String>) {
>>>>>>> 5e0a393 (viewmodelscope dependecies)
    if (errorMessages.isNotEmpty()) {
        for (error in errorMessages) {
            Text(
                text = error,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
<<<<<<< HEAD
<<<<<<< HEAD
}
=======
}

fun validateEmailInputs(email: String): List<String> {
    val errors = mutableListOf<String>()
    if (email.isEmpty()) {
        errors.add("O campo de e-mail está vazio")
    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        errors.add("E-mail inválido")
    }
    return errors
}

fun validatePasswordInputs(password: String): List<String> {
    val errors = mutableListOf<String>()

    val oneLetterAndOneNumberPasswordRegex = Regex("(?=.*[a-zA-Z])(?=.*\\d)")

    if (password.isEmpty()) {
        errors.add("O campo de senha está vazio")
    } else if (password.length < 7) {
        errors.add("A senha deve ter pelo menos 7 caracteres")
    } else if (!oneLetterAndOneNumberPasswordRegex.containsMatchIn(password)) {
        errors.add("A senha deve conter pelo menos uma letra e um número")
    }

    return errors
}

>>>>>>> 5e0a393 (viewmodelscope dependecies)
=======
}
>>>>>>> 003ac35 (refactor:mvvm architecture)
