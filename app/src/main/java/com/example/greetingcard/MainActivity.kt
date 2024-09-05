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

            Spacer(modifier = Modifier.height(36.dp))

            InputFields(passwordState.value, "Senha", KeyboardType.Password, onValueChange = { password -> changePasswordState(password) })

            Spacer(modifier = Modifier.height(36.dp))

            LoginButton()
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
fun InputFields(value: String, title: String, type: KeyboardType, onValueChange: (String) -> Unit) {
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
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Composable
fun LoginButton() {
    Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
        Text(fontSize = 20.sp, text = "Entrar")
    }
}

