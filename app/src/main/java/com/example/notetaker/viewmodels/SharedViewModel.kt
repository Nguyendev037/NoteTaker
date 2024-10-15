package com.example.notetaker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetaker.data.models.Tasks
import com.example.notetaker.data.repositories.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


// this class is Viewmodel for the MVVM architecture pattern
// Viewmodel takes responsibility for control the user interaction and backend site
// HiltViewModel is annotation, the dependencies will auto be retrieved in Activity with
// Android Entry Point
@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: TaskRepository): ViewModel() {


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

}