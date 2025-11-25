package org.kmp.todo.core

import platform.Foundation.NSLog

actual object AppLogs {
    actual fun info(message: String) {
        NSLog("Logs[INFO]",message)
    }
}