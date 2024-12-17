package com.example.saludybienestar.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.saludybienestar.screens.activity.RegisterActivityScreen
import com.example.saludybienestar.screens.dashboard.DashboardScreen
import com.example.saludybienestar.screens.dashboard.DashboardViewModel
import com.example.saludybienestar.screens.information.UserInfoScreen
import com.example.saludybienestar.screens.login.LoginScreen
import com.example.saludybienestar.screens.login.LoginViewModel


@Composable
fun AppNavigation(navController: NavHostController) {

    val loginViewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, loginViewModel)}
        composable("dashboard") { DashboardScreen(navController, DashboardViewModel()) }
        composable("registro") { RegisterActivityScreen(navController) }
        composable("userInfo") { UserInfoScreen(navController) }
    }
}