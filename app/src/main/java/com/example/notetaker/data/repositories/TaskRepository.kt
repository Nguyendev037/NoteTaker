package com.example.notetaker.data.repositories

import com.example.notetaker.data.TaskDao
import com.example.notetaker.data.models.Tasks
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

//Hilt Library is using that return type to decide which one of those provide function
//ViewModelScoped annotation will tell the Dagger to ensure the TaskRepository is alive
// as long as the sharedView model alive

@ViewModelScoped
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val getAllTasks: Flow<List<Tasks>> = taskDao.getAllTasks()
    val sortByLowPriority: Flow<List<Tasks>> = taskDao.sortByLowPriority()
    val sortByHighPriority: Flow<List<Tasks>> = taskDao.sortByHighPriority()

    fun getSelectedTask(taskId: Int): Flow<Tasks> {
        return taskDao.getTaskById(taskId = taskId)
    }

    suspend fun addTask(newTask: Tasks) {
        return taskDao.addTask(task = newTask)
    }

    suspend fun updateTask (task : Tasks) {
        taskDao.updateTask(task = task)
    }

    suspend fun deleteTask(taskId: Int) {
        taskDao.deleteTask(taskId = taskId)
    }

suspend fun deleteAllTask() {
    taskDao.deleteAllTasks()
}

fun searchDatabase(searchQuery:String) : Flow<List<Tasks>>{
    return taskDao.searchDatabase(searchQuery = searchQuery)
}

}