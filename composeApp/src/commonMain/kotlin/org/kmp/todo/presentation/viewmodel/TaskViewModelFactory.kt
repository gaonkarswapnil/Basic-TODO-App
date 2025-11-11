package org.kmp.todo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import org.kmp.todo.domain.usecase.DeleteTaskUseCase
import org.kmp.todo.domain.usecase.EditTaskUseCase
import org.kmp.todo.domain.usecase.GetAllTasksUseCase
import org.kmp.todo.domain.usecase.InsertTaskUseCase
import kotlin.reflect.KClass

class TaskViewModelFactory(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val insertTaskUseCase: InsertTaskUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        return when(modelClass) {
            TaskViewModel::class -> TaskViewModel(getAllTasksUseCase, editTaskUseCase, deleteTaskUseCase, insertTaskUseCase) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.simpleName}")
        }
    }
}