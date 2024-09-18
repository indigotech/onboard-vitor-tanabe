package com.example.greetingcard.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
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
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.greetingcard.model.ListUserItem
import com.example.greetingcard.viewModel.UserListViewModel

@Composable
fun UserListScreen(navController: NavHostController) {
    val viewModel: UserListViewModel = viewModel()
    val userPagingItems = viewModel.userList.collectAsLazyPagingItems()

    Scaffold(modifier = Modifier.padding(32.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("NewUserScreen") }) {
                Text("+")
            }
        }) { padding ->
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(fontWeight = FontWeight.Bold, fontSize = 24.sp, text = "Lista de Usuários")
            if (viewModel.isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            } else if (viewModel.loadErrorMessages.isNotEmpty()) {
                viewModel.loadErrorMessages.forEach { message ->
                    Text(text = message, color = Color.Red, modifier = Modifier.padding(16.dp))
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(userPagingItems.itemCount) { index ->
                        val user = userPagingItems[index]
                        user?.let {
                            UserItem(
                                user = it,
                                onClick = {
                                    navController.navigate("UserDetailScreen/${it.id}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun UserItem(user: ListUserItem, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable{ onClick() }
    ) {
        Text(text = "Nome: ${user.name}")
        Text(text = "Email: ${user.email}")
    }
}