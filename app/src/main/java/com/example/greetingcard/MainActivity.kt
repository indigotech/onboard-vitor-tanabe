package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
<<<<<<< HEAD
<<<<<<< HEAD
=======
import com.example.greetingcard.view.LoginScreen
import com.example.greetingcard.view.UserListScreen
>>>>>>> 003ac35 (refactor:mvvm architecture)
import com.example.greetingcard.ui.theme.GreetingCardTheme
import com.example.greetingcard.view.LoginScreen
import com.example.greetingcard.view.UserListScreen
=======
import com.example.greetingcard.ui.theme.GreetingCardTheme
import com.example.greetingcard.view.LoginScreen
import com.example.greetingcard.view.UserListScreen
>>>>>>> b2ba40f (list comming from repository)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
                MyApp()
            }
        }
    }
}


@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Login" ) {
        composable(route = "Login") { LoginScreen(navController) }
        composable(route = "UserListScreen") { UserListScreen(navController) }
    }
}