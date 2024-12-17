package com.example.saludybienestar.screens

import androidx.room.*
import com.example.saludybienestar.screens.information.UserInfo

@Dao
interface UserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(user: UserInfo)

    @Query("SELECT * FROM user_info LIMIT 1")
    suspend fun getUserInfo(): UserInfo?

    @Update
    suspend fun updateUserInfo(user: UserInfo)
}