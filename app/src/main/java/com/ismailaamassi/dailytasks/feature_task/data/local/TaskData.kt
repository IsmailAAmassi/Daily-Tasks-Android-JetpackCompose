package com.ismailaamassi.dailytasks.feature_task.data.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.ismailaamassi.dailytasks.core.data.DailyTaskTableNames
import com.ismailaamassi.dailytasks.feature_profile.data.local.ProfileData

@Entity(
    tableName = DailyTaskTableNames.TASK_DATA,
    foreignKeys = [ForeignKey(
        entity = ProfileData::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("profile_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class TaskData(
    val title: String,
    val description: String?,
    val category: String,
    val status: Boolean,
    val priority: Int = 1,
    val time: Long,
    val createdAt: Long,
    val updatedAt: Long,
    @ColumnInfo(name = "profile_id", index = true)
    val userId: String,
    @NonNull @PrimaryKey
    val id: String,
)