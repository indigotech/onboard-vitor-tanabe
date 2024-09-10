package com.example.greetingcard.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.greetingcard.viewModel.LoginViewModel
import com.example.greetingcard.viewModel.NewUserViewModel

@Composable
fun NewUserScreen(navHostController: NavHostController) {
    Scaffold(modifier = Modifier.padding(32.dp)) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            val viewModel: NewUserViewModel = viewModel()
            val nameState by viewModel.nameState
            val emailState by viewModel.emailState
            val phoneState by viewModel.phoneState
            val birthDateState by viewModel.birthDateState
            val passwordState by viewModel.passwordState
            val roleState by viewModel.roleState

            Spacer(modifier = Modifier.height(24.dp))
            Text(fontWeight = FontWeight.Bold, fontSize = 24.sp, text = "Novo usuário")
            Spacer(modifier = Modifier.height(24.dp))

            InputFields(
                nameState,
                "Nome",
                KeyboardType.Text,
                onValueChange = { name -> viewModel.updateNameInput(name) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            InputFields(
                emailState,
                "E-mail",
                KeyboardType.Email,
                onValueChange = { email -> viewModel.updateEmailInput(email) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            InputFields(
                phoneState,
                "Telefone",
                KeyboardType.Phone,
                onValueChange = { phone -> viewModel.updatePhoneInput(phone) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            InputFields(
                birthDateState,
                "Data de Nascimento",
                KeyboardType.Number,
                onValueChange = { birthDate -> viewModel.updateBirthDateInput(birthDate) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            InputFields(
                passwordState,
                "Senha",
                KeyboardType.Password,
                onValueChange = { password -> viewModel.updatePasswordInput(password) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            InputFields(
                roleState,
                "Cargo",
                KeyboardType.Text,
                onValueChange = { role -> viewModel.updateRoleInput(role) }
            )

        }
    }
}