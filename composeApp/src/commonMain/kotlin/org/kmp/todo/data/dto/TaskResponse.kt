package org.kmp.todo.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.kmp.todo.domain.model.Task

@Entity(tableName = "Task")
class TaskResponse(
    @PrimaryKey(autoGenerate = true)
    override val id: Int=0,
    override val taskName: String
) : Task(id = id, taskName = taskName)