package com.example.saludybienestar.screens.activity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity_log")
data class ActivityRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val activityType: String,
    val duration: Int, // en minutos
    val calories: Int,
    val date: String // Fecha de la actividad
)