package com.example.greetingcard.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.greetingcard.view.component.ButtonDefault
import com.example.greetingcard.view.component.InputFields
import com.example.greetingcard.view.component.ShowErrors
import com.example.greetingcard.view.component.TitleH1
import com.example.greetingcard.viewModel.LoginViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel: LoginViewModel = viewModel()

    Scaffold(modifier = Modifier.padding(32.dp)) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            TitleH1(title = "Bem vindo(a) à Taqtile!")
            Spacer(modifier = Modifier.height(48.dp))
            InputFields(
                viewModel.emailState,
                "E-mail",
                KeyboardType.Email,
                onValueChange = { email -> viewModel.updateEmailInput(email) }
            )
            ShowErrors(viewModel.emailErrorMessages)
            Spacer(modifier = Modifier.height(36.dp))
            InputFields(
                viewModel.passwordState,
                "Senha",
                KeyboardType.Password,
                onValueChange = { password -> viewModel.updatePasswordInput(password) },
                true
            )
            ShowErrors(viewModel.passwordErrorMessages)
            Spacer(modifier = Modifier.height(36.dp))
            if (!viewModel.isLoading) {
                ButtonDefault("Entrar", onClick = {
                    viewModel.validateAndSetEmailErrors()
                    viewModel.validateAndSetPasswordErrors()
                    if (viewModel.emailErrorMessages.isEmpty() && viewModel.passwordErrorMessages.isEmpty()) {
                        viewModel.authenticateUser(navController)
                    }
                })
            }
            if (viewModel.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            ShowErrors(viewModel.authenticationErrorMessages)
        }
    }
}

