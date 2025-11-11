package org.kmp.todo.domain.usecase

import org.kmp.todo.domain.model.Task
import org.kmp.todo.domain.repository.TaskRepository

class GetAllTasksUseCase constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(): List<Task> {
        return taskRepository.getAllTasks()
    }

}