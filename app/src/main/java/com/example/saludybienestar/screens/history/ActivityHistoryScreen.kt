package com.example.saludybienestar.screens.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class ActivityRecord(
    val id: Int,
    val activityType: String,
    val duration: Int, // en minutos
    val calories: Int,
    val date: String // puedes usar un tipo de fecha más adecuado según tu implementación
)

@Composable
fun ActivityHistoryScreen(navController: NavController) {
    // Lista de ejemplo; en una implementación real, obtén estos datos de tu base de datos o ViewModel
    val activityList = listOf(
        ActivityRecord(1, "Correr", 30, 300, "2024-12-15"),
        ActivityRecord(2, "Nadar", 45, 400, "2024-12-14"),
        // Agrega más registros según sea necesario
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Historial de Actividades",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (activityList.isEmpty()) {
            Text("No hay actividades registradas.")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(activityList) { activity ->
                    ActivityItem(activity)
                }
            }
        }
    }
}

@Composable
fun ActivityItem(activity: ActivityRecord) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Tipo de Actividad: ${activity.activityType}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Duración: ${activity.duration} minutos", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Calorías Quemadas: ${activity.calories}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Fecha: ${activity.date}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}