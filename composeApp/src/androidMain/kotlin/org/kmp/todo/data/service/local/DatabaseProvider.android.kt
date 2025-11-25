package org.kmp.todo.data.service.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.kmp.todo.MainActivity
import org.kmp.todo.MainActivity.Companion.appContext
import org.kmp.todo.data.service.local.database.TaskDatabase

actual object DatabaseProvider {
    actual fun getDatabase(): TaskDatabase {

        var dbPath = appContext.getDatabasePath("task_db").absolutePath

        return Room.databaseBuilder<TaskDatabase>(
            appContext,
            dbPath
        ).build()
    }
}