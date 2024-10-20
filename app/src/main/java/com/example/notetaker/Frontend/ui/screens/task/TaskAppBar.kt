package com.example.notetaker.Frontend.ui.screens.task

import android.R.attr.maxLines
import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notetaker.data.models.Priority
import com.example.notetaker.data.models.Tasks
import com.example.notetaker.ui.theme.*
import com.example.notetaker.util.Action

@Composable
fun TaskAppBar(
    selectedTask :Tasks?,
    navigateToListScreen: (Action) -> Unit
) {
    if(selectedTask == null) {
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    } else {
        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen)
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {

    TopAppBar(
        navigationIcon = {
            BackActionIcon(onBackClicked = navigateToListScreen)
        },
        title = {
            Text(text =  "Add Task")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer, // Background color
            titleContentColor = textColor2 // Text color
        ),
        actions = {
            AddActionIcon(onAddClicked = {})
            CloseAction(onClosedClicked = navigateToListScreen)
        }
    )
}

@Composable
fun BackActionIcon(
    onBackClicked: (Action) -> Unit
) {
    IconButton(onClick = {
        onBackClicked(Action.NO_ACTION)
    }) {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = "Back Arrow",
            tint = textColor2,
            modifier = Modifier.padding(end = 3.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingTaskAppBar(
    selectedTask : Tasks,
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            CloseAction(onClosedClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                maxLines = 1,
                color = textColor2,
                overflow = TextOverflow.Ellipsis
            )

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer, // Background color
        ),
        actions = {
            DeleteAction(onDeletedClicked  = navigateToListScreen)
            UpdateAction(onUpdatedClicked =  navigateToListScreen)
        }
    )
}

@Composable
fun AddActionIcon(
    onAddClicked: (Action) -> Unit
) {
    IconButton(onClick = {
        onAddClicked(Action.ADD)
    }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Task",
            tint = textColor2,
        )
    }
}



@Composable
fun CloseAction(
    onClosedClicked: (Action) -> Unit
) {
    IconButton(onClick = {
        onClosedClicked(Action.NO_ACTION)
    }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Close Icon",
            tint = textColor2,
            modifier = Modifier.padding(end = 3.dp)
        )
    }
}

@Composable
fun DeleteAction(
    onDeletedClicked: (Action) -> Unit
) {
    IconButton(onClick = {
        onDeletedClicked(Action.DELETE)
    }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete Task",
            tint = textColor2,
        )
    }
}

@Composable
fun UpdateAction(
    onUpdatedClicked: (Action) -> Unit
) {
    IconButton(onClick = {
        onUpdatedClicked(Action.UPDATE)
    }) {
        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "Edit Task",
            tint = textColor2,
        )
    }
}


@Composable
@Preview
fun NewTaskAppBarPreview() {
    NewTaskAppBar(navigateToListScreen = {})
}

@Composable
@Preview
fun SelectedTaskAppBarPreview() {
    ExistingTaskAppBar(selectedTask = Tasks(
        id  =  1,
        title = "Nguyen",
        description = "nguyen",
        priority = Priority.Low
    ),navigateToListScreen = {})
}
