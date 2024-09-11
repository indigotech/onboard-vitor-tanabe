package com.example.greetingcard.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

    Scaffold(modifier = Modifier.padding(32.dp)) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            ShowErrors(authenticationErrorMessages)
        }
    }
}

@Composable
private fun Title() {
    Text(
        fontWeight = FontWeight.Bold, fontSize = 24.sp, text = "Bem vindo(a) à Taqtile!"
    )
}



@Composable
private fun LoginButton(onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Text(fontSize = 20.sp, text = "Entrar")
    }
}

@Composable
private fun ShowErrors(errorMessages: List<String>) {
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