package org.kmp.todo.data.service.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import org.kmp.todo.data.dto.TaskResponse
import org.kmp.todo.domain.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM Task")
    suspend fun getAllTasks(): List<TaskResponse>

    @Query("SELECT * FROM Task WHERE id = :id")
    suspend fun getSingleTask(id: Int): TaskResponse

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskResponse)

    @Update
    suspend fun updateTask(task: TaskResponse)

    @Delete
    suspend fun deleteTask(task: TaskResponse)
}