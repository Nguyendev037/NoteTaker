package com.example.notetaker.Frontend.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.listComposable (
    navigateToTaskScreen : (taskId: Int) -> Unit
) {

    composable(
        route = "list/{action}",
        arguments = listOf(navArgument("action") {
            type = NavType.StringType
        })
    ) {

    }

}