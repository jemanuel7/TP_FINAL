package com.example.saludybienestar.screens.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DashboardViewModel: ViewModel() {

    // Estado de las calorías, pasos y progreso
    private val _caloriesConsumed = MutableStateFlow(0)
    val caloriesConsumed = _caloriesConsumed.asStateFlow()

    private val _caloriesBurned = MutableStateFlow(0)
    val caloriesBurned = _caloriesBurned.asStateFlow()

    private val _steps = MutableStateFlow(0)
    val steps = _steps.asStateFlow()

    private val _progress = MutableStateFlow(0f)
    val progress = _progress.asStateFlow()

    // Simula la carga de datos
    init {
        loadDashboardData()
    }

    private fun loadDashboardData() {
        // Aquí podrías llamar a un Repository/API para cargar los datos reales.
        _caloriesConsumed.value = 1500
        _caloriesBurned.value = 800
        _steps.value = 10000
        _progress.value = 0.75f
    }
}