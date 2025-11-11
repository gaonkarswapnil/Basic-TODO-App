package org.kmp.todo.domain.usecase

import org.kmp.todo.domain.repository.TaskRepository

class DeleteTaskUseCase constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(id: Int) {
        taskRepository.deleteTask(id)
    }

}