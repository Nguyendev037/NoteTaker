package com.example.notetaker.Frontend.ui.screens.list

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notetaker.Frontend.navigation.Screens
import com.example.notetaker.data.models.Priority
import com.example.notetaker.data.models.Tasks
import com.example.notetaker.util.Action
import com.example.notetaker.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun ListContent(
    tasks: List<Tasks>,
    navigateToTaskScreen: (Int) -> Unit,
    searchTasks: List<Tasks>,
    searchAppBarState: String,
    lowToHighTasks: List<Tasks>,
    highToLowTasks: List<Tasks>,
    onSwipeToDelete: (Action, Tasks) -> Unit,
) {

    if (searchAppBarState == "OPEN" && searchTasks.isNotEmpty()) {
        HandleListContent(
            tasks = searchTasks,
            navigateToTaskScreen = navigateToTaskScreen,
            onSwipeToDelete = onSwipeToDelete
        )
    } else if (searchAppBarState == "CLOSE" && lowToHighTasks.isNotEmpty()) {
        HandleListContent(
            tasks = lowToHighTasks,
            navigateToTaskScreen = navigateToTaskScreen,
            onSwipeToDelete = onSwipeToDelete
        )
    } else if (searchAppBarState == "CLOSE" && highToLowTasks.isNotEmpty()) {
        HandleListContent(
            tasks = highToLowTasks,
            navigateToTaskScreen = navigateToTaskScreen,
            onSwipeToDelete = onSwipeToDelete
        )
    } else {
        HandleListContent(
            tasks = tasks,
            navigateToTaskScreen = navigateToTaskScreen,
            onSwipeToDelete = onSwipeToDelete
        )

    }

}


@Composable
fun DisplayTasks(
    tasks: List<Tasks>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    onSwipeToDelete: (Action, Tasks) -> Unit,
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
            // rememberSwipeToDismissBoxState will handle the swipe by compare the transition
            val dismissState = rememberSwipeToDismissBoxState()

            var dismissDirection = dismissState.dismissDirection



            val isDismissed = (dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart)
                    && (dismissState.progress >= 1f)


            val scope = rememberCoroutineScope()
            LaunchedEffect(dismissState.dismissDirection, dismissState.progress) {
                if (isDismissed && dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                        scope.launch {
                            delay(200)
                            onSwipeToDelete(Action.DELETE, task)
                            Log.d("task in swipe", task.toString())
                        }
                }



            }

            val degrees by animateFloatAsState(
                if (dismissState.progress in 0f..0.5f) 0f else -45f,
                label = "Degree animation"
            )


            var itemAppeared by remember { mutableStateOf(false) }

            LaunchedEffect(key1 = true) {
                itemAppeared = true
            }



            AnimatedVisibility(
                modifier = Modifier.padding(top=12.dp),
                visible = itemAppeared && !isDismissed,
                enter = expandVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                ),
                exit = shrinkVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )
            ) {
                SwipeToDismissBox(

                    state = dismissState,
                    backgroundContent = { RedBackGround(degrees = degrees) }

                ) {
                    TaskItem(task = task, navigateToTaskScreen = navigateToTaskScreen)
                }
            }



        }
    }
}

// Declare Task Item for render the task component in task List
@Composable
fun TaskItem(
    task: Tasks,
    navigateToTaskScreen: (taskId: Int) -> Unit,
//    onSwipeToDelete : (Action, Tasks) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
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
fun HandleListContent(
    tasks: List<Tasks>,
    navigateToTaskScreen: (Int) -> Unit,
    onSwipeToDelete: (Action, Tasks) -> Unit,
) {
    if (tasks.isEmpty()) {
        ListEmpty();
    } else {
        DisplayTasks(
            tasks = tasks,
            navigateToTaskScreen = navigateToTaskScreen,
            onSwipeToDelete = onSwipeToDelete
        )
    }
}

@Composable
fun RedBackGround(degrees: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
//            .padding(top=12.dp),
        contentAlignment = Alignment.CenterEnd
    )
    {
        Icon(
            modifier = Modifier.rotate(degrees = degrees),
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete",
            tint = Color.White
        )
    }
}


@Composable
@Preview
fun TaskItemPreview() {
    TaskItem(task = Tasks(0, "Title", "Nguyen Qua Dep Trai", Priority.High),
        navigateToTaskScreen = {})
}

@Composable
@Preview
private fun RedBackgroundPreview() {
    Column(modifier = Modifier.height(80.dp)) {
        RedBackGround(degrees = 0f)
    }
}
