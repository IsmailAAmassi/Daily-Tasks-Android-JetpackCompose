package com.ismailaamassi.dailytasks.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ismailaamassi.dailytasks.feature_profile.data.local.ProfileDao
import com.ismailaamassi.dailytasks.feature_profile.data.local.ProfileData
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskDao
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData

@Database(
    entities = [ProfileData::class, TaskData::class],
    version = 1,
    exportSchema = false
)
abstract class DailyTaskDatabase : RoomDatabase() {
    abstract val profileData: ProfileDao
    abstract val taskDao: TaskDao
}