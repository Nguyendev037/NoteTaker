package com.example.notetaker.Frontend.ui.screens.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notetaker.Frontend.navigation.Screens
import com.example.notetaker.data.models.Priority
import com.example.notetaker.data.models.Tasks
import com.example.notetaker.ui.theme.*


@Composable
fun ListContent(
    tasks: List<Tasks>,
    navigateToTaskScreen: (Int) -> Unit
) {

    if (tasks.isEmpty()) {
        ListEmpty();
    } else {
        DisplayTasks(
            tasks = tasks,
            navigateToTaskScreen = navigateToTaskScreen
        )
    }

}


@Composable
fun DisplayTasks(
    tasks: List<Tasks>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    // Update version of Column, it's useful with large size component
    // Due to it's lazy mechanism
    LazyColumn() {
        items(
            items = tasks,
            key = { task -> task.id },
        ) {
            //task fetch from items
            task ->
            TaskItem(task = task, navigateToTaskScreen = navigateToTaskScreen)
        }
    }
}

// Declare Task Item for render the task component in task List
@Composable
fun TaskItem(
    task: Tasks,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        color = MaterialTheme.colorScheme.onSecondary,
        shape = RectangleShape,
        shadowElevation = 2.dp,
        // Navigate function to item page
        // when user click on that surface
        onClick = {
            navigateToTaskScreen(task.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = 12.dp)
                .fillMaxWidth()
        ) {
            //Row layout split the one line screen is 9,
            // Modifier.weight(8f) means, that component width is 8
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    modifier = Modifier.weight(1f),
                    text = task.title,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

//                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    ) {
                        drawCircle(task.priority.color)
                    }
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = task.description,
                color = textColor1,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                // Too much text the other will be ....
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview
fun TaskItemPreview() {
    TaskItem(task = Tasks(0, "Title", "Nguyen Qua Dep Trai", Priority.High),
        navigateToTaskScreen = {})
}
