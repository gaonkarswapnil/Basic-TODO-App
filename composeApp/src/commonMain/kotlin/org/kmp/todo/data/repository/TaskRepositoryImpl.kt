package org.kmp.todo.data.repository

import org.kmp.todo.data.dto.TaskResponse
import org.kmp.todo.domain.model.Task
import org.kmp.todo.domain.repository.TaskRepository

class TaskRepositoryImpl : TaskRepository {

    private var tasks: MutableList<TaskResponse> = mutableListOf(
        TaskResponse(1, "Task1"),
        TaskResponse(2, "Task2"),
        TaskResponse(3, "Task3"),
        TaskResponse(4, "Task4"),
        TaskResponse(5, "Task5")
    )

    private var updatedTask: MutableList<TaskResponse> = mutableListOf()

    init {
        updatedTask = tasks.toMutableList()
    }

    override suspend fun getAllTasks(): List<Task> {
        return updatedTask.toList()
    }

    override suspend fun insertTask(taskName: String) {
        val newId = if (updatedTask.isNotEmpty()) {
            updatedTask.maxOf { it.id } + 1
        } else {
            1
        }
        updatedTask.add(TaskResponse(id = newId, taskName = taskName))

    }

    override suspend fun edit(id: Int, taskName: String) {
        val index = updatedTask.indexOfFirst { it.id == id }
        if (index != -1) {
            updatedTask[index] = TaskResponse(id, taskName)
        }
    }

    override suspend fun deleteTask(id: Int) {
        updatedTask = updatedTask.filter { it.id != id }.toMutableList()
    }

}

