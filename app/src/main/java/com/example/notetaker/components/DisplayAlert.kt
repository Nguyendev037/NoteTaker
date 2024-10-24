package com.example.notetaker.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
//import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DisplayAlert(

    title: String,
    message: String,
    openAlert: Boolean,
    closeAlert: () -> Unit,
    onYesClick: () -> Unit,
) {

    if (openAlert == true) {
        AlertDialog(
            title = {
                Text(
                    textAlign = TextAlign.Center,
                    text = title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                )
            },
            text = {
                Text(
                    textAlign = TextAlign.Center,
                    text = message,
                    fontSize = 16.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onYesClick()
                        closeAlert()
                    }
                ) {
                    Text(
                        text = "OK"
                    )
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { closeAlert() }) {
                    Text(
                        text = "Cancel"
                    )
                }
            },
            onDismissRequest = { closeAlert() }
        )
    }


}

@Composable
@Preview
fun previewAlert() {
    DisplayAlert(
        title = "Nguyen",
        message = "Nguyen",
        openAlert = true,
        closeAlert = {},
        onYesClick = {},
    )
}