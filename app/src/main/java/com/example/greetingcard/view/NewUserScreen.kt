package com.example.greetingcard.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.greetingcard.model.Roles
import com.example.greetingcard.viewModel.NewUserViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

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
    val nameState by viewModel.nameState
    val emailState by viewModel.emailState
    val phoneState by viewModel.phoneState
    val passwordState by viewModel.passwordState
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(fontWeight = FontWeight.Bold, fontSize = 24.sp, text = "Novo usuário")
        Spacer(modifier = Modifier.height(12.dp))

        InputFields(
            nameState,
            "Nome",
            KeyboardType.Text,
            onValueChange = { name -> viewModel.updateNameInput(name) }
        )
        ShowErrors(viewModel.nameErrorMessages.value)
        Spacer(modifier = Modifier.height(12.dp))
        InputFields(
            emailState,
            "E-mail",
            KeyboardType.Email,
            onValueChange = { email -> viewModel.updateEmailInput(email) }
        )
        ShowErrors(viewModel.emailErrorMessages.value)
        Spacer(modifier = Modifier.height(12.dp))
        InputFields(
            phoneState,
            "Telefone",
            KeyboardType.Phone,
            onValueChange = { phone -> viewModel.updatePhoneInput(phone) }
        )
        ShowErrors(viewModel.phoneErrorMessages.value)
        Spacer(modifier = Modifier.height(12.dp))
        InputFields(
            passwordState,
            "Senha",
            KeyboardType.Password,
            onValueChange = { password -> viewModel.updatePasswordInput(password) },
            true
        )
        ShowErrors(viewModel.passwordErrorMessages.value)
        Spacer(modifier = Modifier.height(12.dp))
        UserRoleSelector(viewModel)
        ShowErrors(viewModel.roleErrorMessages.value)
        Spacer(modifier = Modifier.height(12.dp))
        DatePicker(viewModel)
        ShowErrors(viewModel.birthDateErrorMessages.value)
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                viewModel.validateAndSetAllErrors()

                viewModel.addNewUser()

                if(viewModel.addNewUserErrorMessages.value.isEmpty()) {
                    navController.navigate("UserListScreen")
                }

            },
            modifier = Modifier
                .fillMaxWidth(0.7f)

        ) {
            Text(text = "Cadastrar")
        }
        ShowErrors(viewModel.addNewUserErrorMessages.value)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun DatePicker(viewModel: NewUserViewModel) {
    var selectedDate by viewModel.birthDateState
    val dateDialogState = rememberMaterialDialogState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { dateDialogState.show() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Selecione uma data",
                )
                Text("Selecione a data de aniversário", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Data escolhida: $selectedDate",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 16.sp

        )

        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton("Ok")
                negativeButton("Cancelar")
            }
        ) {
            datepicker(
                initialDate = selectedDate,
                title = "Escolha uma data"
            ) { date ->
                viewModel.updateBirthDateInput(date)
            }
        }
    }
}

@Composable
private fun UserRoleSelector(viewModel: NewUserViewModel) {
    var selectedRole by viewModel.roleState

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
                    .clickable { selectedRole = Roles.USER }
                    .padding(8.dp)
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedRole == Roles.USER,
                    onClick = {
                        selectedRole = Roles.USER
                        viewModel.updateRoleInput(Roles.USER)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "User")
            }

            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedRole = Roles.ADMIN }
                    .padding(8.dp)
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedRole == Roles.ADMIN,
                    onClick = {
                        selectedRole = Roles.ADMIN
                        viewModel.updateRoleInput(Roles.ADMIN)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Admin")
            }
        }
    }
}
