package com.example.notetaker.Frontend.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.notetaker.data.models.Priority
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.notetaker.components.PriorityComponent

import com.example.notetaker.ui.theme.*
// Main AppBar
// One Action will be passed from parent to child into 4 times. with the {Name}action composable is
// the smallest and ListAppBar is the largest
@Composable
fun ListAppBar() {
    DefaultListAppBar(onSearchClicked = {}, onSortClicked = {})
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit) {
    TopAppBar(
        title = {
            Text(text = "Tasks")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary, // Background color
            titleContentColor = MaterialTheme.colorScheme.surface, // Text color
        ),
        actions = {
            ListAppBarActions(onSearchClicked = onSearchClicked, onSortClicked = onSortClicked)
        }
    )
}

//This contains all the Action of AppBar
@Composable
fun ListAppBarActions(onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit) {
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
}

//Declare the Search Button with the action search
@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
    IconButton(onClick = { onSearchClicked }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Tasks",
            tint = MaterialTheme.colorScheme.surface // text-color
        )
    }
}

// Declare the Sort Button with the Sort Action
@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit
) {
    // constructor with import setValue and getValue
    // mutableStateOf creates an observable MutableState<T> ,
    // which is an observable type integrated with the compose runtime
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "Sort Tasks",
            tint = MaterialTheme.colorScheme.surface
        )

        DropdownMenu(expanded = expanded, onDismissRequest = { "/Todo" }, modifier = Modifier.background(DropdownBackColor2)) {

            DropdownMenuItem(onClick = {
                expanded = false
                onSortClicked(Priority.Low)
            }, text = { PriorityComponent(priority = Priority.Low) })

            DropdownMenuItem(onClick = {
                expanded = false
                onSortClicked(Priority.Medium)
            }, text = { PriorityComponent(priority = Priority.Medium) })

            DropdownMenuItem(onClick = {
                expanded = false
                onSortClicked(Priority.High)
            }, text = { PriorityComponent(priority = Priority.High) })
        }
    }
}


@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {}
    )

}

