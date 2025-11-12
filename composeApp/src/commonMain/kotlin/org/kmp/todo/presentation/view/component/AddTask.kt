package org.kmp.todo.presentation.view.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.resources.stringResource
import org.kmp.todo.core.AppLogs
import todoapp.composeapp.generated.resources.Res
import todoapp.composeapp.generated.resources.delete_msg
import todoapp.composeapp.generated.resources.delete_task
import todoapp.composeapp.generated.resources.enter_task
import todoapp.composeapp.generated.resources.no
import todoapp.composeapp.generated.resources.update_add
import todoapp.composeapp.generated.resources.yes

@Composable
fun AddTask(
    taskName: String = "",
    onDismissRequest: () -> Unit = {},
    onConfirmation : (text: String) -> Unit = {}
){
    var text by remember { mutableStateOf(taskName) }

    AppLogs.info(taskName)
    AlertDialog(
        title = {
            Text(text = stringResource(Res.string.update_add))
        },
        text = {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text(stringResource(Res.string.enter_task)) }
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation(text)
                }
            ) {
                Text(stringResource(Res.string.yes))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(Res.string.no))
            }
        }
    )
}