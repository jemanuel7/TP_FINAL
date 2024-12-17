package com.example.saludybienestar.screens.information

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val age: Int,
    val weight: Double, // Peso en kg
    val height: Double // Altura en metros
)