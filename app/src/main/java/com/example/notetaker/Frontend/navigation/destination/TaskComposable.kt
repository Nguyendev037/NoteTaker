package com.example.notetaker.Frontend.navigation.destination

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notetaker.util.Action


fun NavGraphBuilder.taskComposable (
    navigateToListScreen : (Action) -> Unit
) {

    composable(
        route = "task/{taskId}",
        arguments = listOf(navArgument("taskId") {
            type = NavType.IntType
        })
    ) {
        navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt("taskId")
    }

}





