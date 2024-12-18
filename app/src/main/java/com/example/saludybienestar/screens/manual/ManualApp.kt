package com.example.saludybienestar.screens.manual



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ManualScreen(navController: NavController ) {
    Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Manual de Uso",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sección 1: Inicio de Sesión
        ManualSection(
            title = "Inicio de Sesión y Registro",
            description = """
                Para acceder a la aplicación, ingrese su correo electrónico y contraseña en la pantalla de inicio. 
                Si no tiene una cuenta, regístrese utilizando un correo válido y una contraseña de al menos 6 caracteres.
            """.trimIndent()
        )

        // Sección 2: Dashboard
        ManualSection(
            title = "Dashboard Diario",
            description = """
                Una vez autenticado, podrá visualizar un resumen de su actividad diaria:
                - Calorías consumidas y quemadas.
                - Pasos realizados.
                - Progreso general del día.
                Puede usar el botón "Registrar Actividad" para añadir nuevas actividades.
            """.trimIndent()
        )

        // Sección 3: Registro de Actividades
        ManualSection(
            title = "Registro de Actividades",
            description = """
                En la pantalla de registro puede añadir detalles de una nueva actividad:
                - Tipo de actividad (ejercicio, descanso, etc.).
                - Duración en minutos.
                - Calorías quemadas.
                Recuerde llenar todos los campos correctamente.
            """.trimIndent()
        )

        // Sección 4: Historial y Configuración
        ManualSection(
            title = "Historial de Actividades",
            description = """
                Consulte el historial de actividades registradas previamente para monitorear su progreso.
                También puede acceder a las configuraciones para ajustar sus datos personales.
            """.trimIndent()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para regresar al Dashboard
        Button(
            onClick = { navController.navigate("dashboard") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al Dashboard")
        }
    }
}

@Composable
fun ManualSection(title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

