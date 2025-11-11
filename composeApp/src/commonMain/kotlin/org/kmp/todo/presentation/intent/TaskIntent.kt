package org.kmp.todo.presentation.intent

import org.kmp.todo.domain.model.Task

sealed class TaskIntent {
    object fetchAllTask: TaskIntent()
    data class insertTask(val taskName: String): TaskIntent()
    data class editTask(val id: Int, val taskName: String): TaskIntent()
    data class deleteTask(val id: Int): TaskIntent()
}