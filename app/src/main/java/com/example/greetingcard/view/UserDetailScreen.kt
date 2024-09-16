package com.example.greetingcard.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.greetingcard.view.component.ShowErrors
import com.example.greetingcard.viewModel.UserDetailViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    userId: String,
    navController: NavHostController
) {
    val viewModel: UserDetailViewModel = viewModel()
    viewModel.loadUserDetail(userId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes do Usuário") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if(viewModel.loadUserDetailErrorMessages.isEmpty()) {
                Text(text = "ID: $userId", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Nome: ${viewModel.nameState}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Email: ${viewModel.emailState}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Data de Nascimento: ${viewModel.birthDateState}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Telefone: ${viewModel.phoneState}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Cargo: ${viewModel.roleState}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(24.dp))
            } else {
                ShowErrors(viewModel.loadUserDetailErrorMessages)
            }
        }
    }
}