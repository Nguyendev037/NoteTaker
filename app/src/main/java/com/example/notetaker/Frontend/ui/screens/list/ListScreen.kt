package com.example.notetaker.Frontend.ui.screens.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ListScreen (
    navigateToTaskScreen: (taskId: Int) -> Unit
) {

    Scaffold(
        topBar = {
            ListAppBar()
        },
        content = {},
        floatingActionButton = {
            ListFAB(onFabClicked = navigateToTaskScreen)
        },
    )

}
@Composable
fun ListFAB (
    onFabClicked: (taskId : Int) -> Unit
) {
    // When user click add there will navigate to add a new task
    // -1 means we do not chose any task but the parameter is mandatory
    FloatingActionButton(onClick = {onFabClicked(-1)}) {
        Icon(imageVector = Icons.Filled.Add,
            contentDescription = "AddButton",
            tint = Color.White
        )
    }
}

@Composable
@Preview
private fun ListScreenPreview() {
    ListScreen(navigateToTaskScreen = {})
}