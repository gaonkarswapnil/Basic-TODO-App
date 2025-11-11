package org.kmp.todo

import android.util.Log

actual object AppLogs {
    actual fun info(message: String) {
        Log.i("Logs[INFO]",message)
    }
}