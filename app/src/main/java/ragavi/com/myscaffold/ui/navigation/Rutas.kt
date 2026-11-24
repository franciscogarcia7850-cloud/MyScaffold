package ragavi.com.myscaffold.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object TaskListRoute

@Serializable
object TaskCreateRoute

@Serializable
data class TaskDetailRoute(val taskId: String)
