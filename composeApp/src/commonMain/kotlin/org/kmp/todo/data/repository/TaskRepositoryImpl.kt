package org.kmp.todo.data.repository

import androidx.compose.animation.core.rememberTransition
import org.kmp.todo.data.dto.TaskResponse
import org.kmp.todo.data.mapper.toDto
import org.kmp.todo.data.service.local.DatabaseProvider
import org.kmp.todo.data.service.local.dao.TaskDao
import org.kmp.todo.domain.model.Task
import org.kmp.todo.domain.repository.TaskRepository

object TaskRepositoryImpl : TaskRepository {

//    private var tasks: MutableList<TaskResponse> = mutableListOf(
//        TaskResponse(1, "Task1"),
//        TaskResponse(2, "Task2"),
//        TaskResponse(3, "Task3"),
//        TaskResponse(4, "Task4"),
//        TaskResponse(5, "Task5")
//    )

//    private var updatedTask: MutableList<TaskResponse> = mutableListOf()

//    init {
//        updatedTask = tasks.toMutableList()
//    }

    val db = DatabaseProvider.getDatabase()

    override suspend fun getAllTasks(): List<Task> {
        return db.task().getAllTasks()
    }

    override suspend fun getSingleTask(id: Int): Task {
        return db.task().getSingleTask(id)
    }

    override suspend fun insertTask(task: Task) {
        db.task().insertTask(task.toDto())

    }

    override suspend fun edit(task: Task) {
        db.task().updateTask(task.toDto())
    }

    override suspend fun deleteTask(task: Task) {
        db.task().deleteTask(task.toDto())
    }

}

