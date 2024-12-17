package com.example.saludybienestar.screens

import androidx.room.*
import com.example.saludybienestar.screens.activity.ActivityRecord

@Dao
interface ActivityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: ActivityRecord)

    @Query("SELECT * FROM activity_log ORDER BY date DESC")
    suspend fun getAllActivities(): List<ActivityRecord>

    @Query("DELETE FROM activity_log")
    suspend fun deleteAllActivities()
}
