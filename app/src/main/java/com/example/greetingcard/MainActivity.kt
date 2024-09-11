package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greetingcard.screens.LoginScreen
import com.example.greetingcard.screens.UserListScreen
import com.example.greetingcard.ui.theme.GreetingCardTheme

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