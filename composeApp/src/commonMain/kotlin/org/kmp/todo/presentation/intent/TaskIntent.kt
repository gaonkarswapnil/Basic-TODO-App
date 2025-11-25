package org.kmp.todo.presentation.intent

import org.kmp.todo.domain.model.Task

sealed class TaskIntent {
    object fetchAllTask: TaskIntent()
    data class insertTask(val task: Task): TaskIntent()
    data class editTask(val task: Task): TaskIntent()
    data class deleteTask(val task: Task): TaskIntent()
    data class fetchSingleTask(val id: Int): TaskIntent()
}