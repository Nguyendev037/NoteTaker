package com.example.notetaker.components

import android.R.attr.angle
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.example.notetaker.data.models.Priority
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }
    // Declare animation for the button
    val angle: Float by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "Angle Animation"
    )

    var parentSize by remember { mutableStateOf(IntSize.Zero) }



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                expanded = true
            }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                shape = MaterialTheme.shapes.small
            )
            .onGloballyPositioned{ coordinates ->
                parentSize = coordinates.size
            },

        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier
                .size(16.dp)
                .weight(weight = 1f)
        ) {
            drawCircle(color = priority.color)
        }
        Text(
            modifier = Modifier
                .weight(weight = 8f),
            text = priority.name,
            style = MaterialTheme.typography.bodyMedium
        )
        IconButton(
            modifier = Modifier
                .alpha(0.5f)
                .rotate(degrees = angle)
                .weight(weight = 1.5f),
            onClick = { expanded = true }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "DropDown Icon"
            )
        }
        DropdownMenu(
            modifier = Modifier
                .width(with(LocalDensity.current) { parentSize.width.toDp() }),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { PriorityComponent(priority = Priority.High) },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.High)
                }
            );
            DropdownMenuItem(
                text = { PriorityComponent(priority =  Priority.Medium) },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.Medium)
                }
            );
            DropdownMenuItem(
                text = { PriorityComponent(priority = Priority.Low) },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.Low)
                }
            );
            DropdownMenuItem(
                text = { PriorityComponent(priority = Priority.None) },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.None)
                }
            );


        }
    }
}

@Composable
@Preview
fun PriorityItemPreview() {
    PriorityDropDown(priority = Priority.High, onPrioritySelected = {})
}

