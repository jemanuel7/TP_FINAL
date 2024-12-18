package com.example.saludybienestar.screens.register

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    private lateinit var auth: FirebaseAuth

    // Inicializamos FirebaseAuth en el init
    init {
        auth = FirebaseAuth.getInstance()
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun validateCredentials(navController: NavController) {
        when {
            _email.value.isBlank() || _password.value.isBlank() -> {
                _errorMessage.value = RegisterConstants.ERROR_FIELDS_EMPTY
            }
            !Patterns.EMAIL_ADDRESS.matcher(_email.value).matches() -> {
                _errorMessage.value = RegisterConstants.ERROR_EMAIL_INVALID
            }
            _password.value.length < 6 -> {
                _errorMessage.value = RegisterConstants.ERROR_PASSWORD_SHORT
            }
            else -> {
                _errorMessage.value = ""
                auth.createUserWithEmailAndPassword(_email.value, _password.value).addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user, navController)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        val errorMessage = when (task.exception) {
                            is FirebaseAuthUserCollisionException -> "El correo electrónico ya está registrado."
                            is FirebaseAuthWeakPasswordException -> "La contraseña es demasiado débil."
                            else -> task.exception?.localizedMessage ?: "Ocurrió un error al crear la cuenta."
                        }

                        _errorMessage.value = errorMessage
                        updateUI(null, navController)
                    }
                }
            }
        }
    }

    object RegisterConstants {
        const val ERROR_FIELDS_EMPTY = "Favor completar todos los campos"
        const val ERROR_EMAIL_INVALID = "El formato del correo no es válido"
        const val ERROR_PASSWORD_SHORT = "La contraseña debe tener al menos 6 caracteres"
    }

    private fun updateUI(user: FirebaseUser?, navController: NavController) {
        if (user != null) {
            navController.navigate("dashboard")
        } else {
            navController.navigate("login")
        }
    }
}