package com.example.notetaker.Frontend.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notetaker.components.PriorityComponent

import com.example.notetaker.ui.theme.*
import java.security.Key

// Main AppBar
// One Action will be passed from parent to child into 4 times. with the {Name}action composable is
// the smallest and ListAppBar is the largest
@Composable
fun ListAppBar() {
//    DefaultListAppBar(onSearchClicked = {}, onSortClicked = {}, onDeleteClicked = {})

    var searchText by remember { mutableStateOf("") }
    SearchAppBar(
        text = searchText,
        onTextChange = {searchText = it},
        onClosedClicked = {},
        onSearchClicked = {},
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Tasks")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary, // Background color
            titleContentColor = MaterialTheme.colorScheme.surface, // Text color
        ),
        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteClicked = onDeleteClicked
            )
        }
    )
}

//This contains all the Action of AppBar
@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAction(onDeleteClicked = onDeleteClicked)
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

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { "/Todo" },
            modifier = Modifier.background(DropdownBackColor2)
        ) {

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

// Declare the Delete Button with the Button Action
@Composable
fun DeleteAction(onDeleteClicked: () -> Unit) {
    IconButton(onClick = { onDeleteClicked }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete action",
            tint = MaterialTheme.colorScheme.surface
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onClosedClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFF3949AB)),
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        //Text input component
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            // Must to use focusedColor and unFocusColor
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(0.5f),
                    text = "Search",
                    color = Color.White,
                )
            },
            textStyle = TextStyle(
                color = Color.White,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            ),
            // for make the input text in on line
            singleLine = true,
            leadingIcon = {
                IconButton(modifier = Modifier
                    .alpha(0.38f),
                    onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Seach Button",
                        tint = MaterialTheme.colorScheme.surface
                    )
                }
            },
            // trailing Icon is the component that appear at the end of line
            trailingIcon = {
                IconButton(onClick = {onClosedClicked}) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close Button",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
               onSearch = {
                   // text in parameter of SearchAppBar is pass to this Function
                   // The onChange function is value = text
                   onSearchClicked(text)
               }
            )


        )
    }
}


@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteClicked = {}
    )

}

@Composable
@Preview
private fun SearchAppBarPreview() {

    var searchText by remember {mutableStateOf("")}

    SearchAppBar(
        text = "Search",
        onTextChange = {searchText = it},
        onClosedClicked = {},
        onSearchClicked = {},
    )
}

