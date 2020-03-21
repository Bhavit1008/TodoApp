package com.example.testtodo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtodo.MainActivity
import com.example.testtodo.R
import com.example.testtodo.RoomFiles.Task

class RecyclerViewAdapter(tasks: ArrayList<Task>, listner: OnItemClickListener) :  RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>(){

    var listtasks : List<Task> = tasks

    var listnertask : OnItemClickListener = listner

    interface OnItemClickListener {
        fun onItemClick(tasks: Task)
    }

    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_list,parent,false))
    }

    override fun getItemCount(): Int {
        return listtasks.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        var currentTask : Task = listtasks[position]
        var currentTitle = currentTask.title
        var currentTaskDesc = currentTask.tastDesc

        holder!!.mtask_desc.text = currentTaskDesc
        holder!!.mtitle.text = currentTitle

        holder.bind(currentTask,listnertask)
    }
    
   internal fun addTasks(task : List<Task>){
        this.listtasks = task
        notifyDataSetChanged()
    }


    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mtitle = itemView.findViewById<TextView>(R.id.title)!!
        var mtask_desc = itemView.findViewById<TextView>(R.id.task_decs)!!

        fun bind(tasks: Task, listener: OnItemClickListener) {
            itemView.setOnClickListener {
                listener.onItemClick(tasks)
            }
        }

    }


}


