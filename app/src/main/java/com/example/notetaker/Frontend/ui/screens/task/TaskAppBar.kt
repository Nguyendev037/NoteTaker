package com.example.notetaker.Frontend.ui.screens.task

import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notetaker.ui.theme.*

@Composable
fun TaskAppBar(navigateToListScreen: (String) -> Unit) {

    NewTaskAppBar(navigateToListScreen = navigateToListScreen)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(
    navigateToListScreen: (String) -> Unit
) {

    TopAppBar(
        navigationIcon = {
            BackActionIcon(onBackClicked = {})
        },
        title = {
            Text(text = "Add Task")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer, // Background color
            titleContentColor = textColor2 // Text color
        ),
        actions = {
            AddActionIcon(onAddClicked = {})
        }
    )
}


@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun BackActionIcon(
    onBackClicked: (String) -> Unit
) {
    IconButton(onClick = {
        onBackClicked("NO_ACTION")
    }) {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = "Back Arrow",
            tint = textColor2,
            modifier = Modifier.padding(end = 3.dp)
        )
    }
}

@Composable
fun AddActionIcon(
    onAddClicked: (String) -> Unit
) {
    IconButton(onClick = {
        onAddClicked("ADD_TASK")
    }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Task",
            tint = textColor2,
        )
    }
}


@Composable
@Preview
fun NewTaskAppBarPreview() {
    NewTaskAppBar(navigateToListScreen = {})
}
