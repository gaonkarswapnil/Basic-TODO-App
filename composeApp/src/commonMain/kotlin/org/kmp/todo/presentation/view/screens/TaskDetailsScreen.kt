package org.kmp.todo.presentation.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.kmp.todo.core.AppLogs
import org.kmp.todo.data.repository.TaskRepositoryImpl
import org.kmp.todo.domain.model.Task
import org.kmp.todo.domain.usecase.DeleteTaskUseCase
import org.kmp.todo.domain.usecase.EditTaskUseCase
import org.kmp.todo.domain.usecase.GetAllTasksUseCase
import org.kmp.todo.domain.usecase.GetSingleTaskUseCase
import org.kmp.todo.domain.usecase.InsertTaskUseCase
import org.kmp.todo.presentation.intent.TaskIntent
import org.kmp.todo.presentation.view.component.AddTask
import org.kmp.todo.presentation.view.component.DeleteTask
import org.kmp.todo.presentation.viewmodel.TaskViewModel
import org.kmp.todo.presentation.viewmodel.TaskViewModelFactory

@Composable
fun TaskDetailsScreen(
    navController: NavController,
    id: Int = 0,
    taskViewModel: TaskViewModel
) {
    var alertForDelete by remember {
        mutableStateOf(false)
    }

    var alertForEdit by remember {
        mutableStateOf(false)
    }

    val data by taskViewModel.singleTaskState.collectAsState()

    val taskName: MutableState<Task?> = remember { mutableStateOf(null) }

    LaunchedEffect(id) {
        taskViewModel.processIntent(TaskIntent.fetchSingleTask(id))
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Task Details",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Task Card
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = taskName.value?.taskName ?: data.task?.taskName.orEmpty(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF222222)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Task ID: ${data.task?.id ?: id}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { alertForEdit = true },
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Text("Edit")
            }

            Button(
                onClick = { alertForDelete = true },
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                Text("Delete")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(onClick = { navController.popBackStack() }) {
            Text("Back to Tasks")
        }
    }

    if (alertForDelete) {
        DeleteTask(
            onConfirmation = {
                AppLogs.info("Confirmation of delete")
                data.task?.let {
                    alertForDelete = false
                    taskViewModel.processIntent(TaskIntent.deleteTask(it))
                    navController.popBackStack()
                }
            },
            onDismissRequest = {
                alertForDelete = false
            }
        )
    }

    if (alertForEdit) {
        AddTask(
            taskName = taskName.value?.taskName ?: data.task?.taskName.orEmpty(),
            flag = true,
            onDismissRequest = {
                alertForEdit = false
            },
            onConfirmation = {
                alertForEdit = false
                taskName.value = it
                taskViewModel.processIntent(TaskIntent.editTask(it))
            }
        )
    }
}