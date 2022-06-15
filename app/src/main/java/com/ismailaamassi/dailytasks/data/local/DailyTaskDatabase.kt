package com.ismailaamassi.dailytasks.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ismailaamassi.dailytasks.data.local.task.TaskDao
import com.ismailaamassi.dailytasks.data.local.task.TaskData
import com.ismailaamassi.dailytasks.data.local.user.UserDao
import com.ismailaamassi.dailytasks.data.local.user.UserData

@Database(
    entities = [UserData::class, TaskData::class],
    version = 1
)
abstract class DailyTaskDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val taskDao: TaskDao
}