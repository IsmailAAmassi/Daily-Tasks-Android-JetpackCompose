package com.ismailaamassi.dailytasks.feature_profile.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ismailaamassi.dailytasks.core.data.DailyTaskTableNames

@Entity(tableName = DailyTaskTableNames.PROFILE_TABLE)
data class ProfileData(
    val username: String,
    val email: String,
    val image: String? = null,
    val token: String? = null,
    @PrimaryKey val id: String,
)