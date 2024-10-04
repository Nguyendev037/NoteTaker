package com.example.notetaker.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.IDN

@Entity(tableName = "food")
data class Food (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title:String
)