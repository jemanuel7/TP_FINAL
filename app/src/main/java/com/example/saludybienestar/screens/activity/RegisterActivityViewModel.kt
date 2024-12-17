package com.example.saludybienestar.screens.activity

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterActivityViewModel : ViewModel() {

    private val _activityType = MutableStateFlow("")
    val activityType = _activityType.asStateFlow()

    private val _duration = MutableStateFlow("")
    val duration = _duration.asStateFlow()

    private val _calories = MutableStateFlow("")
    val calories = _calories.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun onActivityTypeChange(value: String) {
        _activityType.value = value
    }

    fun onDurationChange(value: String) {
        _duration.value = value
    }

    fun onCaloriesChange(value: String) {
        _calories.value = value
    }

    fun validateAndSave(onSuccess: () -> Unit) {
        val durationValue = _duration.value.toIntOrNull() ?: 0
        val caloriesValue = _calories.value.toIntOrNull() ?: 0

        if (_activityType.value.isBlank() || durationValue <= 0 || caloriesValue <= 0) {
            _errorMessage.value = "Por favor, completa todos los campos correctamente."
        } else {
            _errorMessage.value = ""
            // Aquí se puede guardar la actividad en la base de datos
            onSuccess()
        }
    }
}
