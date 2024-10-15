package com.example.notetaker.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notetaker.ui.theme.*
import com.example.notetaker.data.models.Priority


@Composable
fun PriorityComponent (priority: Priority) {

   Row(verticalAlignment = Alignment.CenterVertically){
       // Canvas for drawing an object in UI, here is circle
       Canvas(modifier = Modifier.size(16.dp)) {
           drawCircle(color = priority.color)
       }
       Text(
           modifier = Modifier.padding(start = 12.dp),
           text = priority.name,
           style = MaterialTheme.typography.titleMedium,
           color = DropdownText
       )
   }
}

@Composable
@Preview
fun PriorityPreview() {
    PriorityComponent(priority =  Priority.Low)
}