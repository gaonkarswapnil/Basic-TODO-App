package org.kmp.todo.domain.usecase

import org.kmp.todo.domain.model.Task
import org.kmp.todo.domain.repository.TaskRepository

class EditTaskUseCase constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(task: Task){
        taskRepository.edit(task)
    }

}