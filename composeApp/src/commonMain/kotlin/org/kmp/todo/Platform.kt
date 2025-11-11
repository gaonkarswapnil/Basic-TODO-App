package org.kmp.todo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform