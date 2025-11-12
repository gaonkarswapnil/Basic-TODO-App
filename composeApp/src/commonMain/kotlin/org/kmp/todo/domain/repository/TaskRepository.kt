package org.kmp.todo.domain.repository

import org.kmp.todo.domain.model.Task

interface TaskRepository {

    suspend fun getAllTasks(): List<Task>

    suspend fun getSingleTask(id: Int): Task

    suspend fun insertTask(task: String)

    suspend fun edit(id: Int, task: String)

    suspend fun deleteTask(id: Int)
}