package com.example.notetaker.Frontend.ui.screens.task

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable


@Composable
fun TaskScreen() {


    Scaffold(
        topBar = {TaskAppBar(navigateToListScreen = {})},
        content = {},
    )


}