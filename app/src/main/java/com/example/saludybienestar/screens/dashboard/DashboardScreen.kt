package com.example.saludybienestar.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment


@Composable
fun DashboardScreen(navController: NavController, viewModel: DashboardViewModel) {

    // Observa los datos desde el ViewModel
    val caloriesConsumed by viewModel.caloriesConsumed.collectAsState()
    val caloriesBurned by viewModel.caloriesBurned.collectAsState()
    val steps by viewModel.steps.collectAsState()
    val progress by viewModel.progress.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Resumen Diario", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        InfoCard(title = "Calorías Consumidas", value = "$caloriesConsumed")
        InfoCard(title = "Calorías Quemadas", value = "$caloriesBurned")
        InfoCard(title = "Pasos", value = "$steps")

        Spacer(modifier = Modifier.height(16.dp))

        ProgressBar(title = "Progreso del Día", progress = progress)

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = { navController.navigate("registro") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Registrar Actividad", style = MaterialTheme.typography.bodyLarge)
        }

        /*Redirecciones a otra pantalla*/
        Button(onClick = { navController.navigate("registro") }) {
            Text("Ir a Registro de Actividad")
        }
    }
}

@Composable
fun InfoCard(title: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.bodyLarge)
            Text(value, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun ProgressBar(title: String, progress: Float) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(title, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}


