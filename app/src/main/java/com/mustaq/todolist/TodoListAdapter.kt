package com.mustaq.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_todo.view.*

class TodoListAdapter internal constructor(context: Context,val onToDoClick: onToDoClick) :
    RecyclerView.Adapter<TodoListAdapter.ToDoAdapter>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<ToDoDataModel>() // Cached copy of words

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter {
        val itemView = inflater.inflate(R.layout.row_todo, parent, false)
        return ToDoAdapter(itemView)
    }

    override fun getItemCount() = words.size

    override fun onBindViewHolder(holder: ToDoAdapter, position: Int) {
        val current = words[position]
        holder.bind(current)
    }

    inner class ToDoAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(current: ToDoDataModel) {
            itemView.tvTitle.text = current.id.toString()

            itemView.setOnClickListener {
                    onToDoClick.click(current)
            }
        }


    }

    internal fun setWords(words: List<ToDoDataModel>) {
        this.words = words
        notifyDataSetChanged()
    }
}