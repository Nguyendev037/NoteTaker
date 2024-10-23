package com.example.notetaker.Frontend.ui.screens.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import com.example.notetaker.viewmodels.SharedViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.notetaker.util.Action
import kotlinx.coroutines.launch

@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    // collectAsState: Get the latest value of StateFlow,
    // and auto update when that StateFlow change values

    // LaunchedEffect kindly the same as UseEffect to handle async task
    // It will execute when composition starts
    // LaunchedEffect will auto re-compose when the key change


    // The variable allTasks in sharedViewModel will be pass to allTasks variable in ListScreen,
    // This variable will passed on the ListContent composable
    val allTasks by sharedViewModel.allTasks.collectAsState();

    val searchAppBarState: String = sharedViewModel.searchAppBarState.value
    val searchTextState: String = sharedViewModel.searchTextState.value

    val action by sharedViewModel.action
    val snackBarHostState = remember { SnackbarHostState() }

    // any action change the screen will fetch all again
    LaunchedEffect(key1 = action) { sharedViewModel.getAllTasks() }

    DisplaySnackBar(
        snackBarHostState = snackBarHostState,
        taskTitle = sharedViewModel.title.value,
        handleDatabaseAction = { sharedViewModel.handleDatabaseAction(action = action) },
        action = action,
        onUndoClicked = {
            sharedViewModel.handleDatabaseAction(action = Action.UNDO)
        }
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },

        content = { padding ->
            Column(
                modifier = Modifier.padding(top = padding.calculateTopPadding())
            ) {
                ListContent(tasks = allTasks, navigateToTaskScreen = navigateToTaskScreen)
            }
        },

        floatingActionButton = {
            ListFAB(onFabClicked = navigateToTaskScreen)
        },
    )

}

//Declare ListFAB composable
@Composable
fun ListFAB(
    onFabClicked: (taskId: Int) -> Unit
) {
    // When user click add there will navigate to add a new task
    // -1 means we do not chose any task but the parameter is mandatory
    FloatingActionButton(onClick = { onFabClicked(-1) }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "AddButton",
            tint = Color.White
        )
    }
}


@Composable
fun DisplaySnackBar(
    snackBarHostState: SnackbarHostState,
    handleDatabaseAction: () -> Unit,
    taskTitle: String,
    onUndoClicked : (Action) -> Unit,
    action: Action
) {

    // if any action change this will call handleDatabase function
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {

            Log.d("action in list screen displaySnackbar", action.toString())

            handleDatabaseAction();
            val snackBarHostState = snackBarHostState.showSnackbar(
                message = "${action.name} : $taskTitle",
                actionLabel = setActionLabel(action = action)
            )
            undoDeleteTask(
                action = action,
                snackBarResult = snackBarHostState,
                onUndoClicked = onUndoClicked
            )
        }
    }
}

private fun setActionLabel(action : Action) : String{
    return if (action.name == "DELETE") {
        "UNDO"
    } else {
        "OK"
    }
}

private fun undoDeleteTask(
    action : Action,
    snackBarResult : SnackbarResult,
    onUndoClicked : (Action) -> Unit,
) {
    // SnackBarResult.ActionPerformed will track if user click on label action
    // in SnackBar
    if (snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
        onUndoClicked(action)
    }
}



