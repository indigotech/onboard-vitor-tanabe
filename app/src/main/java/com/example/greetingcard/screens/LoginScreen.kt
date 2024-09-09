package com.example.greetingcard.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.greetingcard.model.AuthenticationRequestBody
import com.example.greetingcard.model.AuthenticationResponse
import com.example.greetingcard.rest.UserAuthenticationRetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.await

fun UserAuthentication(
    email: String,
    password: String,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
    context: Context,
    token: MutableState<String>,
    authenticationErrorMessages: MutableState<List<String>>,
    isLoading: MutableState<Boolean>
) {

    coroutineScope.launch {
        isLoading.value = true
        try {
            val user =
                AuthenticationRequestBody(email, password)
            val response =
                UserAuthenticationRetrofitService.RetrofitInstance.userAuthenticationRetrofitService.authenticateUser(
                    user
                ).await()
            token.value = response.data.token
            navController.navigate("UserListScreen")
            Toast.makeText(
                context,
                "Usuário valido e autenticado",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: Exception) {

            val authErrors = mutableListOf<String>()
            authErrors.add("Usuario ou senha invalidos")
            authenticationErrorMessages.value = authErrors
        } finally {
            isLoading.value = false
        }
    }

}

@Composable
fun LoginScreen(navController: NavHostController) {

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

    Scaffold(modifier = Modifier.padding(32.dp)) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Title()

            Spacer(modifier = Modifier.height(48.dp))

            InputFields(
                emailState.value,
                "E-mail",
                KeyboardType.Email,
                onValueChange = { email -> changeEmailState(email) })

            ShowErrors(emailErrorMessages.value)

            Spacer(modifier = Modifier.height(36.dp))

            InputFields(
                passwordState.value,
                "Senha",
                KeyboardType.Password,
                onValueChange = { password -> changePasswordState(password) },
                true
            )

            ShowErrors(passwordErrorMessages.value)

            Spacer(modifier = Modifier.height(36.dp))

            if (!isLoading.value) {
                LoginButton(onClick = {
                    emailErrorMessages.value = validateEmailInputs(emailState.value)
                    passwordErrorMessages.value = validatePasswordInputs(passwordState.value)

                    if (emailErrorMessages.value.isEmpty() && passwordErrorMessages.value.isEmpty()) {

                        UserAuthentication(
                            email = emailState.value,
                            password = passwordState.value,
                            coroutineScope = coroutineScope,
                            navController = navController,
                            context = context,
                            token = token,
                            authenticationErrorMessages = authenticationErrorMessages,
                            isLoading = isLoading
                        )

                    }
                }
                )
            }

            if (isLoading.value) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }


            ShowErrors(authenticationErrorMessages.value)
        }
    }
}

@Composable
fun Title() {
    Text(
        fontWeight = FontWeight.Bold, fontSize = 24.sp, text = "Bem vindo(a) à Taqtile!"
    )
}

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
    Button(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Text(fontSize = 20.sp, text = "Entrar")
    }
}

@Composable
fun ShowErrors(errorMessages: List<String>) {
    if (errorMessages.isNotEmpty()) {
        for (error in errorMessages) {
            Text(
                text = error,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
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

