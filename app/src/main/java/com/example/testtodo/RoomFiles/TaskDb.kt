package com.example.testtodo.RoomFiles

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [(Task::class)],version = 1,exportSchema = false)
abstract class TaskDb : RoomDatabase(){
    companion object{
        var instance : TaskDb? = null
        fun getDataBase(context: Context) : TaskDb{
            if (instance == null){
                instance = Room.databaseBuilder(context.applicationContext,TaskDb::class.java,"task-db")
                    .allowMainThreadQueries().build()
            }
            return instance as TaskDb
        }
    }
    abstract fun taskDao() : TaskDao
}
