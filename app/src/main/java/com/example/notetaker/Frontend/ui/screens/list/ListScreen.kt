package com.example.notetaker.Frontend.ui.screens.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ListScreen (
    navigateToTaskScreen: (Int) -> Unit
) {

    Scaffold(
        content = {},
        floatingActionButton = {
            ListFAB(navigateToTaskScreen = navigateToTaskScreen)
        },
    )

}
@Composable
fun ListFAB (
    navigateToTaskScreen: (Int) -> Unit
) {
    // When user click add there will navigate to add a new task
    // -1 means we do not chose any task but the parameter is mandatory
    FloatingActionButton(onClick = {navigateToTaskScreen(-1)}) {
        Icon(imageVector = Icons.Filled.Add,
            contentDescription = "AddButton")
    }
}

@Composable
@Preview
private fun ListScreenPreview() {
    ListScreen(navigateToTaskScreen = {})
}