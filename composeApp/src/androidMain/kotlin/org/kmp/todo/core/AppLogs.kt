package org.kmp.todo.core

import android.util.Log

actual object AppLogs {
    actual fun info(message: String) {
        Log.i("Logs[INFO]",message)
    }
}