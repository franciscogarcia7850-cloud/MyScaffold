package ragavi.com.myscaffold.ui.features.tasks

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ragavi.com.myscaffold.data.Task

class TaskViewModel : ViewModel() {
    // Estado privado que se modifica aquí y estado público de solo lectura para la UI
    private val _uiState = MutableStateFlow(
        TaskUiState(
            tasks = listOf(
                Task("1", "Estudiar Kotlin", true),
                Task("2", "Hacer la tarea de Compose", false)
            )
        )
    )
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    fun addTask(title: String, isCompleted: Boolean) {
        _uiState.update { state ->
            val newId = (state.tasks.size + 1).toString()
            state.copy(tasks = state.tasks + Task(newId, title, isCompleted))
        }
    }

    fun toggleTask(id: String) {
        _uiState.update { state ->
            state.copy(
                tasks = state.tasks.map { task ->
                    if (task.id == id) task.copy(isCompleted = !task.isCompleted) else task
                }
            )
        }
    }
}
