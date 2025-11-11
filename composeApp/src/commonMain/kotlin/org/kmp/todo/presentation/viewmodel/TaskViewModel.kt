package org.kmp.todo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.kmp.todo.AppLogs
import org.kmp.todo.domain.model.Task
import org.kmp.todo.domain.usecase.DeleteTaskUseCase
import org.kmp.todo.domain.usecase.EditTaskUseCase
import org.kmp.todo.domain.usecase.GetAllTasksUseCase
import org.kmp.todo.domain.usecase.InsertTaskUseCase
import org.kmp.todo.presentation.intent.TaskIntent
import org.kmp.todo.presentation.state.TaskState

class TaskViewModel constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val insertTaskUseCase: InsertTaskUseCase
): ViewModel() {

    private val _listOfTaskState = MutableStateFlow(TaskState())
    val listOfTaskState = _listOfTaskState.asStateFlow()

    fun processIntent(intent: TaskIntent) {
        viewModelScope.launch {
            when (intent) {
                TaskIntent.fetchAllTask -> fetchAllTask()
                is TaskIntent.insertTask -> insertTask(intent.taskName)
                is TaskIntent.editTask -> editTask(intent.id, intent.taskName)
                is TaskIntent.deleteTask -> deleteTask(intent.id)
            }
        }
    }

    suspend fun fetchAllTask() {
        _listOfTaskState.value = TaskState(loading = true, error = null)
        try {
            val tasks = getAllTasksUseCase()
            AppLogs.info("$tasks")
            _listOfTaskState.value = TaskState(loading = false, listOfTask = tasks)
        } catch (e: Exception) {
            _listOfTaskState.value = TaskState(loading = false, error = e.message)
        }
    }

    suspend fun insertTask(taskName: String){
        _listOfTaskState.value = TaskState(loading = true, error = null)
        try {
            insertTaskUseCase(taskName)
            fetchAllTask()

        } catch (e: Exception) {
            _listOfTaskState.value = TaskState(loading = false, error = e.message)
        }
    }

    suspend fun editTask(id: Int, taskName: String) {
        _listOfTaskState.value = TaskState(loading = true, error = null)
        try {
            editTaskUseCase(id, taskName)
            fetchAllTask()
        } catch (e: Exception) {
            _listOfTaskState.value = TaskState(loading = false, error = e.message)
        }
    }

    suspend fun deleteTask(id: Int) {
        _listOfTaskState.value = TaskState(loading = true, error = null)
        try {
            deleteTaskUseCase(id)
            fetchAllTask()
        } catch (e: Exception) {
            _listOfTaskState.value = TaskState(loading = false, error = e.message)
        }
    }
}
