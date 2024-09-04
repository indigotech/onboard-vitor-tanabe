package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(color = Color.Cyan)
                .padding(32.dp),
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                fontWeight = FontWeight.Bold, fontSize = 24.sp, text = "Bem vindo(a) à Taqtile!"
            )

            Spacer(modifier = Modifier.height(48.dp))

            Column() {
                Text(text = "E-mail")
                OutlinedTextField(
                    value = emailState.value,
                    onValueChange = { emailState.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            Column() {
                Text(text = "Senha")
                OutlinedTextField(
                    value = passwordState.value,
                    onValueChange = { passwordState.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text(fontSize = 20.sp, text = "Entrar")
            }
        }
    }
}