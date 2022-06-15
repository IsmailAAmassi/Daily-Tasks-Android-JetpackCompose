package com.ismailaamassi.dailytasks.data.local.task

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ismailaamassi.dailytasks.data.local.DailyTaskTableNames

@Dao
interface TaskDao {

    @Insert
    fun insertTask(taskData: TaskData): Long

    @Update
    fun updateTask(taskData: TaskData): Int

    @Query("DELETE FROM ${DailyTaskTableNames.TASK_DATA} WHERE id =:id")
    fun deleteTask(id: String): Int

    @Query("SELECT * FROM ${DailyTaskTableNames.TASK_DATA}")
    fun getTasks(): List<TaskData>

    @Query("DELETE FROM ${DailyTaskTableNames.TASK_DATA}")
    fun clearTasks()
}