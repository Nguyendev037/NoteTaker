package com.example.notetaker.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notetaker.data.models.Food
import com.example.notetaker.data.models.Tasks
import com.example.notetaker.data.TaskDao
// This class for creating a whole dataset which contains tables or entities
@Database(entities = [Tasks::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    // declare function to map Dao Interface
    abstract fun taskDao() : TaskDao

}