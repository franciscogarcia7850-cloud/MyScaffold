package ragavi.com.myscaffold.ui.features.tasks

import ragavi.com.myscaffold.data.Task

data class TaskUiState(
    val tasks: List<Task> = emptyList()
)
