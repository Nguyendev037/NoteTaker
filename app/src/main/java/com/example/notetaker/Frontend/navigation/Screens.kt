package com.example.notetaker.Frontend.navigation

import androidx.navigation.NavHostController
import com.example.notetaker.util.Action

class Screens(navController: NavHostController) {

    // Declare a function for navigation. The syntax for using navigate is navController.navigate("")
    // "Action" is type of variable, here is the Enum Class.
    // The action helps us to define the action from repository in sharedViewmodel,
    // easier in controlling the flow of app activities
    val list : (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            // after the navigation success,
            // this function compose will be deleted in the stack of app
            // to release the memory
            popUpTo("list/{action}") {inclusive= true}
        }
    }


    val task: (Int) -> Unit = { taskID ->
        navController.navigate("task/${taskID}")
    }


}