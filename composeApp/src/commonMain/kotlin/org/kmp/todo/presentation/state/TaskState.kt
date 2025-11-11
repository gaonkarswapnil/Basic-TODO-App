package org.kmp.todo.presentation.state

import org.kmp.todo.domain.model.Task

data class TaskState (
    val loading: Boolean = false,
    val listOfTask: List<Task> = emptyList(),
    val task: Task? = null,
    val error: String? = null
)