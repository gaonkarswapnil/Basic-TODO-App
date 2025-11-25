package org.kmp.todo.domain.usecase

import org.kmp.todo.domain.model.Task
import org.kmp.todo.domain.repository.TaskRepository

class DeleteTaskUseCase constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(task: Task) {
        taskRepository.deleteTask(task)
    }

}