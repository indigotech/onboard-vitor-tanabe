package com.example.greetingcard.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.greetingcard.model.Roles
import com.example.greetingcard.view.component.InputFields
import com.example.greetingcard.view.component.ShowErrors
import com.example.greetingcard.viewModel.NewUserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewUserScreen(navController: NavHostController) {
    Scaffold(modifier = Modifier.padding(32.dp)) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            item {
                NewUserForm(navController)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun NewUserForm(navController: NavHostController) {
    val viewModel: NewUserViewModel = viewModel()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(fontWeight = FontWeight.Bold, fontSize = 24.sp, text = "Novo usuário")
        Spacer(modifier = Modifier.height(12.dp))
        InputFields(
            viewModel.nameState,
            "Nome",
            KeyboardType.Text,
            onValueChange = { name -> viewModel.updateNameInput(name) }
        )
        ShowErrors(viewModel.nameErrorMessages)
        Spacer(modifier = Modifier.height(12.dp))
        InputFields(
            viewModel.emailState,
            "E-mail",
            KeyboardType.Email,
            onValueChange = { email -> viewModel.updateEmailInput(email) }
        )
        ShowErrors(viewModel.emailErrorMessages)
        Spacer(modifier = Modifier.height(12.dp))
        InputFields(
            viewModel.phoneState,
            "Telefone",
            KeyboardType.Phone,
            onValueChange = { phone -> viewModel.updatePhoneInput(phone) }
        )
        ShowErrors(viewModel.phoneErrorMessages)
        Spacer(modifier = Modifier.height(12.dp))
        InputFields(
            viewModel.passwordState,
            "Senha",
            KeyboardType.Password,
            onValueChange = { password -> viewModel.updatePasswordInput(password) },
            true
        )
        ShowErrors(viewModel.passwordErrorMessages)
        Spacer(modifier = Modifier.height(12.dp))
        InputFields(
            viewModel.birthDateState,
            "Data de Aniversário",
            KeyboardType.Number,
            onValueChange = { birthDate -> viewModel.updateBirthDateInput(birthDate) }
        )
        ShowErrors(viewModel.birthDateErrorMessages)
        Spacer(modifier = Modifier.height(12.dp))
        UserRoleSelector(viewModel)
        ShowErrors(viewModel.roleErrorMessages)
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                viewModel.validateAndSetAllErrors()

                viewModel.addNewUser()

                if(viewModel.addNewUserErrorMessages.isEmpty()) {
                    navController.navigate("UserListScreen")
                }

            },
            modifier = Modifier
                .fillMaxWidth(0.7f)

        ) {
            Text(text = "Cadastrar")
        }
        ShowErrors(viewModel.addNewUserErrorMessages)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun UserRoleSelector(viewModel: NewUserViewModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Selecione o cargo", fontSize = 16.sp)

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable { viewModel.roleState = Roles.USER }
                    .padding(8.dp)
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = viewModel.roleState == Roles.USER,
                    onClick = {
                        viewModel.roleState = Roles.USER
                        viewModel.updateRoleInput(Roles.USER)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "User")
            }

            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable { viewModel.roleState = Roles.ADMIN }
                    .padding(8.dp)
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = viewModel.roleState == Roles.ADMIN,
                    onClick = {
                        viewModel.roleState = Roles.ADMIN
                        viewModel.updateRoleInput(Roles.ADMIN)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Admin")
            }
        }
    }
}