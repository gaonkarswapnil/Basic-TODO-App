package org.kmp.todo.domain.usecase

import org.kmp.todo.domain.repository.TaskRepository

class InsertTaskUseCase constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(taskName: String){
        taskRepository.insertTask(taskName)
    }

}