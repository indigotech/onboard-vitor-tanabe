package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greetingcard.ui.theme.GreetingCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {

    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val emailErrorMessages = remember { mutableStateOf(listOf<String>()) }
    val passwordErrorMessages = remember { mutableStateOf(listOf<String>()) }

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

            InputFields(emailState.value, "E-mail", KeyboardType.Email, onValueChange = { email -> changeEmailState(email) })

            ShowErrors(emailErrorMessages)

            Spacer(modifier = Modifier.height(36.dp))

            InputFields(passwordState.value, "Senha", KeyboardType.Password, onValueChange = { password -> changePasswordState(password) }, true)

            ShowErrors(passwordErrorMessages)

            Spacer(modifier = Modifier.height(36.dp))

            LoginButton(onClick = {
                emailErrorMessages.value = validateEmailInputs(emailState.value)
            }
            )
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
fun InputFields(value: String, title: String, type: KeyboardType, onValueChange: (String) -> Unit, isPassword: Boolean = false) {
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
fun ShowErrors(errorMessages: androidx.compose.runtime.MutableState<List<String>>) {
    if (errorMessages.value.isNotEmpty()) {
        for (error in errorMessages.value) {
            Text(
                text = error,
                color = androidx.compose.ui.graphics.Color.Red,
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
    // Regex para conter pelo menos uma letra e um número
    val passwordRegex = Regex("(?=.*[a-zA-Z])(?=.*\\d)")

    if (password.isEmpty()) {
        errors.add("O campo de senha está vazio")
    } else if (password.length < 7) {
        errors.add("A senha deve ter pelo menos 7 caracteres")
    } else if (!passwordRegex.containsMatchIn(password)) {
        errors.add("A senha deve conter pelo menos uma letra e um número")
    }

    return errors
}
