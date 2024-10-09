package com.example.notetaker.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notetaker.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

// The main reason we declare DatabaseModule, due to reduce the TaskDataBase to everywhere
// we tried to used, we just need to inject the DatabaseModule to handle that, this will be easier
// for expanding project and easier to control the flow logic of project

//Provides Annotation is tried to tell Dagger how to create an Dependency
// SingletonComponent is for Application level injection

// Hilt Library is using that return type to decide which one of those provide function

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        TaskDatabase::class.java,
        "task"
    ).build()

    // The function provideDatabase has been called when passed "TaskDatabase" parameter
    // When we call this function, this will create TaskDataBase variable and then return
    // The TaskDao function variable which is Dao function for the Task
    @Singleton
    @Provides
    fun provideDao(database: TaskDatabase) =  database.taskDao()
}

