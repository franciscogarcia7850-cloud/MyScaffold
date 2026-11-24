package ragavi.com.myscaffold.ui.features.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFormScreen(
    viewModel: TaskViewModel,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var title by rememberSaveable { mutableStateOf("") }
    var isCompleted by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Captura de Tarea") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Campo 1: ID (Inhabilitado porque se autogenera incrementalmente)
            OutlinedTextField(
                value = "Auto-generado (Próximo: ${uiState.tasks.size + 1})",
                onValueChange = {},
                label = { Text("ID de la Tarea") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )

            // Campo 2: Título
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título de la Tarea") },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo 3: Completada
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isCompleted,
                    onCheckedChange = { isCompleted = it }
                )
                Text("Completada")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de Registro
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        viewModel.addTask(title, isCompleted)
                        onNavigateBack() // Regresa a la lista al completar el registro
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = title.isNotBlank() // Solo se activa si hay texto
            ) {
                Text("Registrar Tarea")
            }
        }
    }
}