package com.example.saludybienestar.screens.information

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun UserInfoScreen(navController: NavController, viewModel: UserInfoViewModel = viewModel()) {
    // Obteniendo los valores del ViewModel
    val name = viewModel.name.collectAsState()
    val age = viewModel.age.collectAsState()
    val weight = viewModel.weight.collectAsState()
    val height = viewModel.height.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título principal
        Text(
            text = "Información del Usuario",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Tarjetas de información del usuario
        UserInfoCard(label = "Nombre", value = name.value)
        UserInfoCard(label = "Edad", value = "${age.value} años")
        UserInfoCard(label = "Peso", value = "${weight.value} kg")
        UserInfoCard(label = "Estatura", value = "${height.value} m")

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para regresar al Dashboard
        Button(
            onClick = { navController.navigate("dashboard") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Regresar al Dashboard")
        }

        // Botón para ir al manual de la app
        Button(
            onClick = { navController.navigate("manual") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Manual sobre la app")
        }
    }
}

@Composable
fun UserInfoCard(label: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = androidx.compose.material3.CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = label, style = MaterialTheme.typography.bodyLarge)
            Text(text = value, style = MaterialTheme.typography.bodyMedium)
        }
    }
}