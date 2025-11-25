package org.kmp.todo.data.mapper

import org.kmp.todo.data.dto.TaskResponse
import org.kmp.todo.domain.model.Task

fun Task.toDto(): TaskResponse{
    return TaskResponse(
        this.id,
        this.taskName
    )
}