package com.example.notetaker.Frontend.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.example.notetaker.Frontend.navigation.destination.listComposable
import com.example.notetaker.Frontend.navigation.destination.taskComposable
import com.example.notetaker.viewmodels.SharedViewModel

@Composable
fun SetUpNavigation (
    navController: NavHostController,
    sharedViewModel: SharedViewModel
){
    // function remember like useEffect in React only re-render when navController change
    val screen = remember(navController){
        Screens(navController = navController)
    }

    // NavHost is the main Navigation to controller all the Navigation
    // In here we defined all the composable screen, with two parameter props are
    // navController and startDestination
    NavHost(
        navController = navController,
        startDestination = "list/NO_ACTION"
    ) {
        listComposable(
            navigateToTaskScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
    }
}
