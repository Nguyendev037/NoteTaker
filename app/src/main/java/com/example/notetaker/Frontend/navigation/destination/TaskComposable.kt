package com.example.notetaker.Frontend.navigation.destination

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notetaker.Frontend.ui.screens.task.TaskScreen
import com.example.notetaker.util.Action
import com.example.notetaker.viewmodels.SharedViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit,
) {

    composable(
        route = "task/{taskId}",
        arguments = listOf(navArgument("taskId") {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt("taskId")
        // call repository function in shareViewedModel
        sharedViewModel.getSelectedTask(taskId = taskId)
        // The variable selectedTask in sharedViewModel will be pass to
        // selectedTask variable in TaskComposable,
        // This variable will passed on the TaskScreen composable
        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask) {
            sharedViewModel.updateTaskFields(selectedTask = selectedTask)
        }

        TaskScreen(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen,
            sharedViewModel = sharedViewModel
        )
    }

}





