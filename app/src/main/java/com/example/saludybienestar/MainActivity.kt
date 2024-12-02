package com.example.saludybienestar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.saludybienestar.screens.AppNavigation
import com.example.saludybienestar.ui.theme.SaludyBienestarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SaludyBienestarTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}
