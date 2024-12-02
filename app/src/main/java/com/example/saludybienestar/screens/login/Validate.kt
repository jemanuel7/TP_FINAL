package com.example.saludybienestar.screens.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf("")

    fun validateCredentials(navController: NavController) {
        when {
            email.isBlank() || password.isBlank() -> {
                errorMessage = "Favor completar todos los campos"
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                errorMessage = "El formato del correo no es válido"
            }
            password.length < 6 -> {
                errorMessage = "La contraseña debe tener al menos 6 caracteres"
            }
            else -> {
                errorMessage = ""
                navController.navigate("dashboard")
            }
        }
    }
}