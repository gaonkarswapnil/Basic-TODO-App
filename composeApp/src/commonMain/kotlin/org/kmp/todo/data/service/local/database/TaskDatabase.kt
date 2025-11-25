package org.kmp.todo.data.service.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.kmp.todo.data.dto.TaskResponse
import org.kmp.todo.data.service.local.dao.TaskDao

@Database(entities = [TaskResponse::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun task(): TaskDao
}