package org.kmp.todo.domain.repository

import org.kmp.todo.domain.model.Task

interface TaskRepository {

    suspend fun getAllTasks(): List<Task>

    suspend fun getSingleTask(id: Int): Task

    suspend fun insertTask(task: Task)

    suspend fun edit(task: Task)

    suspend fun deleteTask(task: Task)
}