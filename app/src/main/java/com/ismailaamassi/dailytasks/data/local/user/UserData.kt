package com.ismailaamassi.dailytasks.data.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ismailaamassi.dailytasks.data.local.DailyTaskTableNames

@Entity(tableName = DailyTaskTableNames.USER_DATA)
data class UserData(
    val username: String,
    val email: String,
    val image: String? = null,
    val token: String? = null,
    @PrimaryKey val id: String,
)