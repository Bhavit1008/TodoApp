package com.example.testtodo

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtodo.Adapter.RecyclerViewAdapter
import com.example.testtodo.RoomFiles.Task
import com.example.testtodo.RoomFiles.TaskDb
import com.example.testtodo.ViewModel.TaskViewModel

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.OnItemClickListener {


    private var taskRecyclerView: RecyclerView? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null

    private var viewModel: TaskViewModel? = null

    private var db: TaskDb? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var array : ArrayList<Any>? = null
        db = TaskDb.getDataBase(this)

        taskRecyclerView = findViewById(R.id.recycler)
        recyclerViewAdapter = RecyclerViewAdapter(arrayListOf(),this)

        taskRecyclerView!!.layoutManager = LinearLayoutManager(this)
        taskRecyclerView!!.adapter = recyclerViewAdapter

        viewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)

        viewModel!!.getlistTask().observe(this, Observer { tasks -> recyclerViewAdapter!!.addTasks(tasks) })
        fab.setOnClickListener { view ->
            var intent = Intent(applicationContext, TaskDetailsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all_items -> {
                deleteAllContacts()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun deleteAllContacts(){
        db!!.taskDao().deleteAllTasks()
    }

    override fun onItemClick(task: Task) {
        var intent = Intent(applicationContext, TaskDetailsActivity::class.java)
        intent.putExtra("idTask", task.id)
        startActivity(intent)
    }
}
