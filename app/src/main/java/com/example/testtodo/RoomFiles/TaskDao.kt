package com.example.testtodo.RoomFiles

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao{
    @Query("select * from tasks")
    fun getAllTasks() : LiveData<List<Task>>

    @Query("select * from tasks where idTask in (:id)")
    fun getTaskById(id: Int): Task

    @Query("delete from tasks")
    fun deleteAllTasks()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)
}