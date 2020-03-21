package com.example.testtodo.ViewModel

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.testtodo.RoomFiles.Task
import com.example.testtodo.RoomFiles.TaskDb

class TaskViewModel(application: Application) : AndroidViewModel(application){
    var tasklist : LiveData<List<Task>>
    val appdb : TaskDb

    init {
        appdb = TaskDb.getDataBase(this.getApplication())
        tasklist = appdb.taskDao().getAllTasks()
    }

    fun getlistTask() : LiveData<List<Task>>{
        return tasklist
    }

    fun addTask(task: Task) {
        addAsyncTask(appdb).execute(task)
    }

    class addAsyncTask(db : TaskDb) : AsyncTask<Task ,Void ,Void >(){
        var taskdb = db
        override fun doInBackground(vararg params: Task): Void? {
            taskdb.taskDao().insertContact(params[0])
            return null
        }

    }
}