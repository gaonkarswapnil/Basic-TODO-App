package org.kmp.todo.core

actual object AppLogs {
    actual fun info(message: String) {
        print(message)
    }
}