package org.kmp.todo.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.kmp.todo.presentation.navigation.SetUp

@Composable
fun AppHost() {
    val navController = rememberNavController()
    SetUp(navController)
}