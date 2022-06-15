package com.ismailaamassi.dailytasks.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ismailaamassi.dailytasks.data.local.DailyTaskTableNames

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userData: UserData): Long

    @Query("DELETE FROM ${DailyTaskTableNames.USER_DATA}")
    fun deleteUser()

    @Query("SELECT * FROM ${DailyTaskTableNames.USER_DATA} LIMIT 1")
    fun getUser(): UserData?
}