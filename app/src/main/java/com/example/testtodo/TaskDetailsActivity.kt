package com.example.testtodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.testtodo.RoomFiles.Task
import com.example.testtodo.RoomFiles.TaskDao
import com.example.testtodo.RoomFiles.TaskDb
import com.example.testtodo.ViewModel.TaskViewModel
import kotlinx.android.synthetic.main.activity_task_details.*

class TaskDetailsActivity : AppCompatActivity() {

    var taskDao : TaskDao? = null
    var viewModel : TaskViewModel? = null

    var currentTask : Int? = null
    var task : Task? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)
        var db : TaskDb = TaskDb.getDataBase(this)
        taskDao = db.taskDao()
        viewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        currentTask = intent.getIntExtra("idTask",-1)
        if (currentTask != -1){
            setTitle("edit task")
            task = taskDao!!.getTaskById(currentTask!!)
            title_edit_text.setText(task!!.title)
            task_edit_text.setText(task!!.tastDesc)
        }
        else{
            setTitle("Add task")
            invalidateOptionsMenu()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_items,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.done_item -> {
                if (currentTask == -1) {
                    saveTask()
                    Toast.makeText(this, "save task", Toast.LENGTH_SHORT).show()
                } else {
                    updateTask()
                    Toast.makeText(this, "task updated", Toast.LENGTH_SHORT).show()
                }

                finish()
            }
            R.id.delete_item -> {
                deleteTask()
                Toast.makeText(this, "task deleted", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        if (currentTask == -1) {
            menu.findItem(R.id.delete_item).isVisible = false
        }
        return true
    }

    fun saveTask(){
        var etitle = title_edit_text.text.toString()
        var etask = task_edit_text.text.toString()
        var mtask = Task(0,etitle,etask)
        viewModel!!.addTask(mtask)
    }

    fun updateTask(){
        var etitle = title_edit_text.text.toString()
        var etask = task_edit_text.text.toString()
        var t = Task(task!!.id,etitle,etask)
        taskDao!!.updateTask(t!!)
    }

    fun deleteTask(){
        taskDao!!.deleteTask(task!!)
    }



}
