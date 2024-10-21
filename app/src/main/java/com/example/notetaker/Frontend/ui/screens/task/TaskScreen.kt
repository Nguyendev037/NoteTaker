package com.example.notetaker.Frontend.ui.screens.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.notetaker.data.models.Tasks
import com.example.notetaker.util.Action
import androidx.compose.ui.Modifier
import com.example.notetaker.data.models.Priority
import com.example.notetaker.viewmodels.SharedViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
@Composable
fun TaskScreen(
    selectedTask :Tasks?,
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {

    val title: String  = sharedViewModel.title.value
    val description: String  = sharedViewModel.description.value
    val priority: Priority  = sharedViewModel.priority.value


    Scaffold(
        topBar = {TaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )},
        content = {
            padding -> Column (
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