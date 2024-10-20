package com.example.notetaker.Frontend.ui.screens.task

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.notetaker.data.models.Tasks
import com.example.notetaker.util.Action


@Composable
fun TaskScreen(
    selectedTask :Tasks?,
    navigateToListScreen: (Action) -> Unit
) {


    Scaffold(
        topBar = {TaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )},
        content = {},
    )


}