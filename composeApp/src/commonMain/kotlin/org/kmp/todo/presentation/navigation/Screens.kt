package org.kmp.todo.presentation.navigation

sealed class Screens (val route: String){
    object ListOfTasks: Screens("list_of_tasks")

    object TaskDetails: Screens("task_details/{taskId}"){
        fun createRoute(taskId: Int) = "task_details/$taskId"
    }
}