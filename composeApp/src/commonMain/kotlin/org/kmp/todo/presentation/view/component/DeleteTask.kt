package org.kmp.todo.presentation.view.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import todoapp.composeapp.generated.resources.Res
import todoapp.composeapp.generated.resources.delete_msg
import todoapp.composeapp.generated.resources.delete_task
import todoapp.composeapp.generated.resources.no
import todoapp.composeapp.generated.resources.yes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteTask(
    onConfirmation: () -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    AlertDialog(
        title = {
            Text(text = stringResource(Res.string.delete_task))
        },
        text = {
            Text(text = stringResource(Res.string.delete_msg))
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirmation
            ) {
                Text(stringResource(Res.string.yes))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(stringResource(Res.string.no))
            }
        }
    )
}