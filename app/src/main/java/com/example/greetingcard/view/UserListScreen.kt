<<<<<<< HEAD
<<<<<<< HEAD
package com.example.greetingcard.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.greetingcard.model.UserListItem
import com.example.greetingcard.viewModel.UserListViewModel

@Composable
fun UserListScreen(navController: NavHostController) {
    val viewModel: UserListViewModel = viewModel()
    val isLoading by viewModel.isLoading
    val userList by viewModel.userList
    val loadErrorMessages by viewModel.loadErrorMessages

    Scaffold(modifier = Modifier.padding(32.dp)) { padding ->
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(fontWeight = FontWeight.Bold, fontSize = 24.sp, text = "Lista de Usuários")
            Spacer(modifier = Modifier.height(24.dp))
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            } else if (loadErrorMessages.isNotEmpty()) {
                loadErrorMessages.forEach { message ->
                    Text(text = message, color = Color.Red, modifier = Modifier.padding(16.dp))
                }
            } else {
                LazyColumn(
                    contentPadding = padding,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(userList) { userListItem ->
                        UserItem(userListItem = userListItem)
                    }
                }
            }
        }
    }
}

@Composable
private fun UserItem(userListItem: UserListItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Nome: ${userListItem.name}")
        Text(text = "Email: ${userListItem.email}")
    }
}
=======
package com.example.greetingcard.screens
=======
package com.example.greetingcard.view
>>>>>>> 003ac35 (refactor:mvvm architecture)

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun UserListScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Successful!", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("Login") }) {
            Text("Continue")
        }
    }
<<<<<<< HEAD
}
>>>>>>> 5e0a393 (viewmodelscope dependecies)
=======
}
>>>>>>> 003ac35 (refactor:mvvm architecture)
