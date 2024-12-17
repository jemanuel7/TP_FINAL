package com.example.saludybienestar.screens.information

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserInfoViewModel : ViewModel() {
    private val _name = MutableStateFlow("Juan Pérez")
    val name = _name.asStateFlow()

    private val _age = MutableStateFlow(28)
    val age = _age.asStateFlow()

    private val _weight = MutableStateFlow(70.0) // Peso en kg
    val weight = _weight.asStateFlow()

    private val _height = MutableStateFlow(1.75) // Altura en metros
    val height = _height.asStateFlow()
}