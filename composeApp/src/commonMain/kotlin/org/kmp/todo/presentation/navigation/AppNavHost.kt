package org.kmp.todo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.kmp.todo.data.repository.TaskRepositoryImpl
import org.kmp.todo.domain.usecase.DeleteTaskUseCase
import org.kmp.todo.domain.usecase.EditTaskUseCase
import org.kmp.todo.domain.usecase.GetAllTasksUseCase
import org.kmp.todo.domain.usecase.GetSingleTaskUseCase
import org.kmp.todo.domain.usecase.InsertTaskUseCase
import org.kmp.todo.presentation.view.screens.ListOfTasksScreen
import org.kmp.todo.presentation.view.screens.TaskDetailsScreen
import org.kmp.todo.presentation.viewmodel.TaskViewModel
import org.kmp.todo.presentation.viewmodel.TaskViewModelFactory

@Composable
fun SetUp(
    navController: NavHostController
){
    val taskViewModel: TaskViewModel = viewModel(
        factory = TaskViewModelFactory(
            GetAllTasksUseCase(TaskRepositoryImpl),
            GetSingleTaskUseCase(TaskRepositoryImpl),
            EditTaskUseCase(TaskRepositoryImpl),
            DeleteTaskUseCase(TaskRepositoryImpl),
            InsertTaskUseCase(TaskRepositoryImpl)
        )
    )

    NavHost(
        navController,
        startDestination = Screens.ListOfTasks.route
    ){
        composable(Screens.ListOfTasks.route){
            ListOfTasksScreen(
                taskViewModel = taskViewModel,
                onTaskClicked = {taskId ->
                    navController.navigate(Screens.TaskDetails.createRoute(taskId))
                }
            )
        }

        composable(
            Screens.TaskDetails.route,
            listOf(navArgument("taskId"){ type = NavType.IntType })
        ) {backStack ->

            val taskId = backStack.arguments?.getInt("taskId") ?: 0
            TaskDetailsScreen(
                navController,
                taskId,
                taskViewModel = taskViewModel
            )

        }
    }
}