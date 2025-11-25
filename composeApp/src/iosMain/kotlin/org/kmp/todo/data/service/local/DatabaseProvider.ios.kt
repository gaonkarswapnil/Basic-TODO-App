package org.kmp.todo.data.service.local

import androidx.room.Room
import org.kmp.todo.data.service.local.database.TaskDatabase
import platform.Foundation.NSHomeDirectory

actual object DatabaseProvider {
    actual fun getDatabase(): TaskDatabase {
        var dbPath = NSHomeDirectory() + "task_db"
        return Room.databaseBuilder<TaskDatabase>(
            dbPath
        ).build()
    }
}