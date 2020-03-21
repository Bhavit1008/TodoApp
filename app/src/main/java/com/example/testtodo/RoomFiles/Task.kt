package com.example.testtodo.RoomFiles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idTask")
    var id :Int = 0,

    @ColumnInfo(name = "title")
    var title:String = "",

    @ColumnInfo(name = "taskDesc")
    var tastDesc : String = ""
)