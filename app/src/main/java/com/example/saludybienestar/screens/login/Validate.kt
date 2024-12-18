package com.example.saludybienestar.screens.login

import android.content.ContentValues.TAG
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    object LoginConstants {
        const val ERROR_FIELDS_EMPTY = "Favor completar todos los campos"
        const val ERROR_EMAIL_INVALID = "El formato del correo no es válido"
        const val ERROR_PASSWORD_SHORT = "La contraseña debe tener al menos 6 caracteres"
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun validateCredentials(
        navController: NavController
    ) {
        when {
            _email.value.isBlank() || _password.value.isBlank() -> {
                _errorMessage.value = LoginConstants.ERROR_FIELDS_EMPTY
            }
            !Patterns.EMAIL_ADDRESS.matcher(_email.value).matches() -> {
                _errorMessage.value = LoginConstants.ERROR_EMAIL_INVALID
            }
            _password.value.length < 6 -> {
                _errorMessage.value = LoginConstants.ERROR_PASSWORD_SHORT
            }
            else -> {
                _errorMessage.value = ""
                auth.signInWithEmailAndPassword(_email.value, _password.value)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            navController.navigate("dashboard")
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                        }
                    }
            }
        }
    }
}