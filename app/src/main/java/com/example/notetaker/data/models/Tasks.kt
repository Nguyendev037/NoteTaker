package com.example.notetaker.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// Task model also, create table with Entity annotation
@Entity(tableName = "task")
data class Tasks (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)