package com.example.notetaker.Frontend.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notetaker.Frontend.ui.screens.list.ListScreen


// NavGraphBuilder is inside the NavHost, it controls the navigate setup for "each page"
// This is ListComposable face wil
fun NavGraphBuilder.listComposable (
    navigateToTaskScreen : (taskId: Int) -> Unit
) {
    //composable is the component from navigate lib to declare the ui of the scree.
    // ListScreen is the main UI page of List Screen.
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