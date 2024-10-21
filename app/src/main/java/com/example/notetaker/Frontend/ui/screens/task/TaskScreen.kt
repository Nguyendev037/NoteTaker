package com.example.notetaker.Frontend.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.notetaker.data.models.Tasks
import com.example.notetaker.util.Action
import androidx.compose.ui.Modifier
import com.example.notetaker.data.models.Priority
import com.example.notetaker.viewmodels.SharedViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TaskScreen(
    selectedTask: Tasks?,
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {

    val title: String = sharedViewModel.title.value
    val description: String = sharedViewModel.description.value
    val priority: Priority = sharedViewModel.priority.value

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->

                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else {
                        if (sharedViewModel.validateField()) {
                            navigateToListScreen(action)
                        } else {
                            displayToast(context = context)
                        }
                    }

                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(top = padding.calculateTopPadding())
            )
            {
                // Because of "MutableState hook", those variable
                // will be watched and auto updated if the value is changed
                TaskContent(
                    title = title,
                    onTitleChange = {
                        sharedViewModel.updateTitle(it)
                    },
                    description = description,
                    onDescriptionChange = {
                        sharedViewModel.description.value = it
                    },
                    priority = priority,
                    onPrioritySelected = {
                        sharedViewModel.priority.value = it
                    },
                )
            }
        },
    )

}

fun displayToast(context: Context) {
    Toast.makeText(
        context,
        "Filed empty",
        Toast.LENGTH_SHORT,
    ).show()
}
