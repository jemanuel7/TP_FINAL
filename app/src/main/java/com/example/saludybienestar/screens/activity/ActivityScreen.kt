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
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun RegisterActivityScreen(navController: NavController, viewModel: RegisterActivityViewModel = viewModel()) {
    val activityType by viewModel.activityType.collectAsState()
    val duration by viewModel.duration.collectAsState()
    val calories by viewModel.calories.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Registro de las Actividades",
            style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Campo para tipo de actividad
        TextField(
            value = activityType,
            onValueChange = { viewModel.onActivityTypeChange(it) },
            label = { Text("Tipo de Actividad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para duración
        TextField(
            value = duration,
            onValueChange = { viewModel.onDurationChange(it) },
            label = { Text("Duración (minutos)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para calorías quemadas
        TextField(
            value = calories,
            onValueChange = { viewModel.onCaloriesChange(it) },
            label = { Text("Calorías Quemadas") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar actividad
        Button(
            onClick = {
                viewModel.validateAndSave {
                    navController.navigate("dashboard")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Actividad")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.validateAndSave {
                    navController.navigate("userInfo")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Información del usuario")
        }

        // Mensaje de error
        if (errorMessage.isNotBlank()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}

