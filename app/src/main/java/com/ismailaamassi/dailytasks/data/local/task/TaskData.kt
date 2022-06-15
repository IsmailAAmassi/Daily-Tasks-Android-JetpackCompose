package com.ismailaamassi.dailytasks.data.local.task

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.ismailaamassi.dailytasks.data.local.DailyTaskTableNames
import com.ismailaamassi.dailytasks.data.local.user.UserData

@Entity(
    tableName = DailyTaskTableNames.TASK_DATA,
    foreignKeys = [ForeignKey(
        entity = UserData::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class TaskData(
    val title: String,
    val description: String,
    val status: Boolean,
    val priority: Int,
    val time: Long,
    val createdAt: Long,
    val updatedAt: Long,
    @ColumnInfo(name = "user_id", index = true)
    val userId: String,
    @NonNull @PrimaryKey
    val id: String,
)