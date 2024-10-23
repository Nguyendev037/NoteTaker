package com.example.notetaker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notetaker.data.models.Tasks
import kotlinx.coroutines.flow.Flow


// This interface will contained all SQL queries for the Database
// Flow for the asynchronous stream data
// @Dao annotation for the get the Dao Library
@Dao
interface TaskDao {

    @Query("Select * from task order by id ASC")
    fun getAllTasks(): Flow<List<Tasks>>
    // = will compare exactly with the parameter
    @Query("Select * from task where id = :taskId")
    fun getTaskById(taskId: Int): Flow<Tasks>

    // onConflict for handle error process, the replace for overwrite the data with the same id
    // suspend function can stop to priority another function to run, the it's resume
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task : Tasks)

    @Update
    suspend fun updateTask(task: Tasks)


    @Query("Delete from task where id =:taskId")
    suspend fun deleteTask(taskId: Int)

    @Query("Delete from task")
    suspend fun deleteAllTasks()

    // Like will compare almost the same
    @Query("Select * from task where title like :searchQuery or description like :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Tasks>>

    @Query ("Select * from task order by case when priority like 'L%' then 1 when priority like 'M%' then 2 when priority like 'H%' then 3 END")
    fun sortByLowPriority() : Flow<List<Tasks>>

    @Query ("Select * from task order by case when priority like 'H%' then 1 when priority like 'M%' then 2 when priority like 'L%' then 3 END")
    fun sortByHighPriority() : Flow<List<Tasks>>

}