package com.example.notetaker.Frontend.navigation.destination

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notetaker.Frontend.ui.screens.list.ListScreen
import com.example.notetaker.util.toAction
import com.example.notetaker.viewmodels.SharedViewModel


// NavGraphBuilder is inside the NavHost, it controls the navigate setup for "each page"
// This is ListComposable face wil
fun NavGraphBuilder.listComposable (
    navigateToTaskScreen : (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    //composable is the component from navigate lib to declare the ui of the scree.
    // ListScreen is the main UI page of List Screen.
    composable(
        route = "list/{action}",
        // Defined all the route must has a parameter
        // the action variable can be anything if it's string
        // This will lead to list screen composable with whatever action
        arguments = listOf(navArgument("action") {
            type = NavType.StringType
        })
    ) {
        // We will get back the "action" props from navigateToListScreen function
        // From the action, we can easier controller the function in sharedViewmodel
        navBackStackEntry ->
        val action  = navBackStackEntry.arguments?.getString("action").toAction();

        Log.d("action", action.toString())

        LaunchedEffect(key1 = action) {
            // update the action value in shareViewModel
            // "the action" variable will be auto updated after each navigate function
            sharedViewModel.action.value = action;
        }

        ListScreen (
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }

}