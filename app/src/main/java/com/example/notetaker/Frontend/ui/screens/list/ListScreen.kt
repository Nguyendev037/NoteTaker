package com.example.notetaker.Frontend.ui.screens.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.notetaker.viewmodels.SharedViewModel
import com.example.notetaker.Frontend.ui.screens.list.ListContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
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
    LaunchedEffect(key1 = true) { sharedViewModel.getAllTasks() }

    // The variable allTasks in sharedViewModel will be pass to allTasks variable in ListScreen,
    // This variable will passed on the ListContent composable
    val allTasks by sharedViewModel.allTasks.collectAsState();

    Log.d("allTasks", allTasks.toString());

    val searchAppBarState: String = sharedViewModel.searchAppBarState.value
    val searchTextState: String = sharedViewModel.searchTextState.value

    Scaffold(

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

