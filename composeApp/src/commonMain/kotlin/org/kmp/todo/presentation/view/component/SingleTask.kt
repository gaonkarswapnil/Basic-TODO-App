package org.kmp.todo.presentation.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kmp.todo.core.AppLogs
import org.kmp.todo.domain.model.Task
import todoapp.composeapp.generated.resources.Res
import todoapp.composeapp.generated.resources.delete
import todoapp.composeapp.generated.resources.edit
import todoapp.composeapp.generated.resources.ic_delete
import todoapp.composeapp.generated.resources.ic_edit
import todoapp.composeapp.generated.resources.ic_eye
import todoapp.composeapp.generated.resources.view

@Composable
fun SingleTask(
    task: Task,
//    onEditClick: (taskName: String) -> Unit = {},
//    onDeleteClick: () -> Unit = {},
    navigate: (id: Int) -> Unit = {}
) {
//    var alertForDelete by  remember {
//        mutableStateOf(false)
//    }
//
//    var alertForEdit by remember {
//        mutableStateOf(false)
//    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = task.taskName,
                style = MaterialTheme.typography.bodyLarge
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Box(
//                    modifier = Modifier
//                        .size(40.dp)
//                        .background(Color(0xFFFFB74D), shape = CircleShape)
//                        .clickable { alertForEdit = true },
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        painter = painterResource(Res.drawable.ic_edit),
//                        contentDescription = stringResource(Res.string.edit),
//                        tint = Color.White,
//                        modifier = Modifier.size(22.dp)
//                    )
//                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFF9E9E9E), shape = CircleShape)
                        .clickable{
                            navigate(task.id)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_eye),
                        contentDescription = stringResource(Res.string.view),
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

        }
    }

//    if (alertForDelete){
//        DeleteTask(
//            onConfirmation = {
//                AppLogs.info("Confirmation of delete")
//                alertForDelete = false
//                onDeleteClick()
//            },
//            onDismissRequest = {
//                alertForDelete = false
//            }
//        )
//    }
//
//    if(alertForEdit){
//        AddTask(
//            taskName = task.taskName,
//            onDismissRequest = {
//                alertForEdit = false
//            },
//            onConfirmation = {
//                alertForEdit = false
//                onEditClick(it)
//            }
//        )
//    }
}