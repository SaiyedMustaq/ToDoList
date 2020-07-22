package com.mustaq.todolist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), onToDoClick {
    private val newWordActivityRequestCode = 1
    private lateinit var todoViewModel: ToDoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TodoListAdapter(this, this)
        recyclerView.adapter = adapter
        todoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)


        todoViewModel.allWords.observe(this, Observer {
            it?.let {
                adapter.setWords(it)
            }
        })

        floatingActionButton.setOnClickListener {
            startActivityForResult(
                Intent(this, AddToDoActivity::class.java),
                newWordActivityRequestCode
            )
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newWordActivityRequestCode && resultCode == RESULT_OK) {
            if (data != null) {
                Log.e(
                    TAG,
                    "onActivityResult: ${data.getParcelableExtra<ToDoDataModel>("EXTRA_REPLAY")}"
                )
                data.getParcelableExtra<ToDoDataModel>("EXTRA_REPLY")?.let { dataModel ->
                    val word = ToDoDataModel(
                        dataModel.id,
                        dataModel.priority,
                        dataModel.title,
                        dataModel.description
                    )
                    todoViewModel.insert(word)
                }
            } else {
                Log.e(TAG, "onActivityResult: $data")
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Empty Not Saved",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        const val TAG = "MAINACTIVITY"
    }

    override fun click(todoModel: ToDoDataModel) {
        /*val editIntent = Intent(this, AddToDoActivity::class.java)
        editIntent.putExtra("EditUser", editIntent)*/
        //Log.e(TAG, "click: ${todoModel.id}")
    }
}