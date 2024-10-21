package com.example.notetaker.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetaker.data.models.Tasks
import com.example.notetaker.data.repositories.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.example.notetaker.data.models.Priority
import dagger.Provides

// this class is Viewmodel for the MVVM architecture pattern
// Viewmodel takes responsibility for control the user interaction and backend site
// HiltViewModel is annotation, the dependencies will auto be retrieved in Activity with
// Android Entry Point
@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: TaskRepository): ViewModel() {

    // Get All Tasks Process
    val searchAppBarState: MutableState<String> = mutableStateOf("CLOSE")
    val searchTextState : MutableState<String> = mutableStateOf("")

    // MutableFlow is a special Flow not only for handle async but also can be change the
    // state data over time and can be observed
    private val _allTasks = MutableStateFlow<List<Tasks>>(emptyList())
    // StateFlow is for "read-only" state data, it's only change based on the function
    val allTasks: StateFlow<List<Tasks>> = _allTasks

    fun getAllTasks() {
        // ViewmodelScope is a special Scope for ViewModel class of coroutine
        // This is for perform background tasks asynchronously while keeping
        // them bound to lifecycle of the viewmodel
        viewModelScope.launch{
            repository.getAllTasks.collect{
                _allTasks.value = it
            }
        }
    }

    // Get  Selected Task Process
    private val _selectedTask : MutableStateFlow<Tasks?>  = MutableStateFlow(null)
    val selectedTask : StateFlow<Tasks?> = _selectedTask

    fun getSelectedTask(taskId : Int) {
        viewModelScope.launch {
            repository.getSelectedTask(taskId = taskId).collect {
                    task -> _selectedTask.value = task
            }
        }
    }

    // Handle Edit Tasks Process
    val id : MutableState<Int> = mutableIntStateOf(0)
    val title : MutableState<String> = mutableStateOf("")
    val description : MutableState<String> = mutableStateOf("")
    val priority : MutableState<Priority> = mutableStateOf(Priority.Low)

    fun updateTaskFields(selectedTask : Tasks?) {
        if(selectedTask != null) {
            id.value =  selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        } else {
            id.value =  0
            title.value = ""
            description.value = ""
            priority.value = Priority.Low
        }
    }

    // Expanded update function with check title length
    fun updateTitle(newTitle : String) {
        if (newTitle.length  < 20) {
            title.value = newTitle
        }
    }
    // validation function to check empty
    fun validateField(): Boolean {
     return (title.value.isNotEmpty() && description.value.isNotEmpty())
    }


}