package com.example.notetaker.Frontend.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notetaker.Frontend.ui.screens.list.ListScreen

fun NavGraphBuilder.listComposable (
    navigateToTaskScreen : (taskId: Int) -> Unit
) {

    composable(
        route = "list/{action}",
        // Defined all the route must has a parameter
        arguments = listOf(navArgument("action") {
            type = NavType.StringType
        })
    ) {
        ListScreen (navigateToTaskScreen = navigateToTaskScreen)
    }

}