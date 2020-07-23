package com.mustaq.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_todo.view.*

class TodoListAdapter internal constructor(val context: Context, val onToDoClick: onToDoClick) :
    RecyclerView.Adapter<TodoListAdapter.ToDoAdapter>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<TodoModel>() // Cached copy of words

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
        fun bind(current: TodoModel) {
            itemView.tvTitle.text = current.taskTitle.toString()
            itemView.tvDesc.text = current.taskDescription.toString()

            when (current.taskPriority) {
                1, 2, 3 -> {
                    itemView.tvPriority.text = current.taskPriority.toString()
                    itemView.tvPriority.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorRed
                        )
                    )
                    itemView.tvPriority.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorWhite
                        )
                    )


                }
                4, 5, 6 -> {
                    itemView.tvPriority.text = current.taskPriority.toString()
                    itemView.tvPriority.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorGreen
                        )
                    )
                    itemView.tvPriority.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorWhite
                        )
                    )
                }
                7, 8, 9 -> {
                    itemView.tvPriority.text = current.taskPriority.toString()
                    itemView.tvPriority.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorYellow
                        )
                    )
                    itemView.tvPriority.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorBlack
                        )
                    )
                }
            }



            itemView.setOnClickListener {
                onToDoClick.click(current)
            }
        }


    }

    internal fun setWords(words: List<TodoModel>) {
        this.words = words
        notifyDataSetChanged()
    }
}