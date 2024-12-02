package com.example.saludybienestar.screens.activity

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun RegisterActivityScreen(navController: NavController) {
    var activityType by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Registro de las Actividades", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Campo para tipo de actividad
        TextField(
            value = activityType,
            onValueChange = { activityType = it },
            label = { Text("Tipo de Actividad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para duración
        TextField(
            value = duration,
            onValueChange = { duration = it },
            label = { Text("Duración (minutos)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para calorías quemadas
        TextField(
            value = calories,
            onValueChange = { calories = it },
            label = { Text("Calorías Quemadas") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar actividad
        Button(
            onClick = {
                val durationValue = duration.toIntOrNull() ?: 0
                val caloriesValue = calories.toIntOrNull() ?: 0

                if (activityType.isBlank() || durationValue <= 0 || caloriesValue <= 0) {
                    errorMessage = "Por favor, completa todos los campos correctamente."
                } else {
                    // Aquí guardaríamos los datos en la base de datos o llamamos al ViewModel
                    errorMessage = ""
                    navController.navigate("dashboard")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Actividad")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Mensaje de error
        if (errorMessage.isNotBlank()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}

