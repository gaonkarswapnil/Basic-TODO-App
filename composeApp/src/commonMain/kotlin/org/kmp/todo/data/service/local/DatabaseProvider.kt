package org.kmp.todo.data.service.local

import org.kmp.todo.data.service.local.database.TaskDatabase

expect object DatabaseProvider {
    fun getDatabase(): TaskDatabase
}