package org.kmp.todo.data.dto

import org.kmp.todo.domain.model.Task

class TaskResponse(
    id: Int,
    taskName: String
) : Task(id = id, taskName = taskName)