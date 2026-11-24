package ragavi.com.myscaffold.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ragavi.com.myscaffold.ui.features.tasks.TaskDetailScreen
import ragavi.com.myscaffold.ui.features.tasks.TaskFormScreen
import ragavi.com.myscaffold.ui.features.tasks.TaskList
import ragavi.com.myscaffold.ui.features.tasks.TaskViewModel

@Composable
fun AppNavigation() {
    // Un solo ViewModel compartido por todas las pantallas
    val taskViewModel: TaskViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = TaskListRoute) {
        composable<TaskListRoute> {
            TaskList(
                viewModel = taskViewModel,
                onNavigateToCreate = { navController.navigate(TaskCreateRoute) },
                // Se envía el id de la tarea como argumento
                onNavigateToDetail = { id -> navController.navigate(TaskDetailRoute(taskId = id)) }
            )
        }
        composable<TaskCreateRoute> {
            TaskFormScreen(
                viewModel = taskViewModel,
                onNavigateBack = { navController.popBackStack(TaskListRoute, inclusive = false) }
            )
        }
        composable<TaskDetailRoute> { backStackEntry ->
            // Se recibe el argumento
            val args: TaskDetailRoute = backStackEntry.toRoute()
            TaskDetailScreen(
                taskId = args.taskId,
                viewModel = taskViewModel,
                onNavigateBack = { navController.popBackStack(TaskListRoute, inclusive = false) }
            )
        }
    }
}
