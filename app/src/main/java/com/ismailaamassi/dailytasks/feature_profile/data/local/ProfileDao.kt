package com.ismailaamassi.dailytasks.feature_profile.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ismailaamassi.dailytasks.core.data.DailyTaskTableNames

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun loginProfile(profileData: ProfileData): Long

    @Query("DELETE FROM ${DailyTaskTableNames.PROFILE_TABLE}")
    fun deleteLoggedProfile()

    @Query("SELECT * FROM ${DailyTaskTableNames.PROFILE_TABLE} LIMIT 1")
    fun getLoggedProfile(): ProfileData?
}