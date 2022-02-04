package com.example.angelaToDo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/* Describing how to display data
 */
class TaskItemAdapter(val listOfTask: List<String>,
                      val longClickListener:OnLongClickListener) :
    RecyclerView.Adapter<TaskItemAdapter.ViewHolder>(){

    interface OnLongClickListener{
        fun onItemLongClicked(position: Int)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //Reference to layout elements
        val textView: TextView

        init {
            textView= itemView.findViewById(android.R.id.text1)
            itemView.setOnLongClickListener{
                longClickListener.onItemLongClicked(adapterPosition)
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // New holder instance
        return ViewHolder(contactView)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOfTask.get(position)
        holder.textView.text = item

    }

    override fun getItemCount(): Int {
        return listOfTask.size
    }
}