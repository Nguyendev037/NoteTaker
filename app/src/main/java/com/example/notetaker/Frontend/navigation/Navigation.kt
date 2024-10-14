package com.example.notetaker.Frontend.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.example.notetaker.Frontend.navigation.destination.listComposable
import com.example.notetaker.Frontend.navigation.destination.taskComposable

@Composable
fun SetUpNavigation (navController: NavHostController){
    // function remember like useEffect in React only re-render when navController change
    val screen = remember(navController){
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = "list/NO_ACTION"
    ) {
        listComposable(
            navigateToTaskScreen = screen.task,
        )
        taskComposable(
            navigateToListScreen = screen.list
        )
    }
}