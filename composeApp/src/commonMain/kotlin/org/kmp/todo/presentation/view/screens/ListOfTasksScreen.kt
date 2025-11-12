package org.kmp.todo.presentation.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kmp.todo.data.repository.TaskRepositoryImpl
import org.kmp.todo.domain.usecase.DeleteTaskUseCase
import org.kmp.todo.domain.usecase.EditTaskUseCase
import org.kmp.todo.domain.usecase.GetAllTasksUseCase
import org.kmp.todo.domain.usecase.GetSingleTaskUseCase
import org.kmp.todo.domain.usecase.InsertTaskUseCase
import org.kmp.todo.presentation.intent.TaskIntent
import org.kmp.todo.presentation.view.component.AddTask
import org.kmp.todo.presentation.view.component.SingleTask
import org.kmp.todo.presentation.viewmodel.TaskViewModel
import org.kmp.todo.presentation.viewmodel.TaskViewModelFactory
import todoapp.composeapp.generated.resources.Res
import todoapp.composeapp.generated.resources.add
import todoapp.composeapp.generated.resources.add_icon


@Composable
fun ListOfTasksScreen(
    onTaskClicked: (id: Int) -> Unit,
    taskViewModel: TaskViewModel
) {
//    val taskRepositoryImpl = TaskRepositoryImpl()
//    val taskViewModel: TaskViewModel = viewModel(
//        factory = TaskViewModelFactory(
//            GetAllTasksUseCase(taskRepositoryImpl),
//            GetSingleTaskUseCase(taskRepositoryImpl),
//            EditTaskUseCase(taskRepositoryImpl),
//            DeleteTaskUseCase(taskRepositoryImpl),
//            InsertTaskUseCase(taskRepositoryImpl)
//        )
//    )

    var alertForInsert by remember {
        mutableStateOf(false)
    }

    val data by taskViewModel.listOfTaskState.collectAsState()

    LaunchedEffect(true) {
        taskViewModel.processIntent(TaskIntent.fetchAllTask)
    }

    when {
        data.loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        data.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: ${data.error}")
            }
        }
        else -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
                LazyColumn {
                    items(data.listOfTask) { task ->
                        SingleTask(
                            task,
                            navigate ={ onTaskClicked(task.id) }
                        )
                    }
                }

                FloatingActionButton(
                    onClick = {
                        alertForInsert = true
                    },
                    modifier = Modifier.align(Alignment.BottomEnd),
                    containerColor = Color(0xFF81C784)
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(Res.drawable.add_icon),
                        contentDescription = stringResource(Res.string.add),
                        tint = Color.White
                    )
                }
            }

            if(alertForInsert){
                AddTask(
                    onDismissRequest = {
                        alertForInsert = false
                    },
                    onConfirmation = {
                        taskViewModel.processIntent(TaskIntent.insertTask(it))
                        alertForInsert = false
                    }
                )
            }
        }
    }
}
