package com.mustaq.todolist

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.material_dialog.*

class MainActivity : AppCompatActivity(), onToDoClick {
    private val newTodoCode = 1
    private val editTodoCode = 2
    lateinit var adapter: TodoListAdapter
    private lateinit var todoViewModel: ToDoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        adapter = TodoListAdapter(this, this)
        recyclerView.adapter = adapter
        todoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)


        todoViewModel.allWords.observe(this, Observer {
            it?.let {
                adapter.setWords(it)
            }
        })

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddToDoActivity::class.java)
            startActivityForResult(
                intent,
                newTodoCode
            )
        }
    }

    override fun click(todoModel: TodoModel) {
        /*val editIntent = Intent(this, AddToDoActivity::class.java)
        editIntent.putExtra(EDIT_INTENT, todoModel)
        startActivity(editIntent)*/

    }

    override fun deleteSingleNote(taskId: Int) {
        deleteSingleNoteDialog(taskId)
    }

    private fun deleteSingleNoteDialog(taskId: Int) {
        val dialog = Dialog(this@MainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.material_dialog)
        dialog.show()
        dialog.tvDialogTitle.text = "Are you went to sure to remove?"
        dialog.dialogNo.setOnClickListener { dialog.dismiss() }
        dialog.dialogYes.setOnClickListener {
            todoViewModel.deleteSingleItemViewModel(taskId)
            dialog.dismiss()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newTodoCode && resultCode == RESULT_OK) {
            data?.getParcelableExtra<TodoModel>(AddToDoActivity.EXTRA_REPLY)?.let { dataModel ->
                val todoModel = TodoModel(
                    dataModel.getTaskTitle(),
                    dataModel.getTaskPriority(),
                    dataModel.getTaskDescription()
                )
                todoViewModel.insert(todoModel)
            }
            toast(this, "Data Save")

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
        const val EDIT_INTENT = "EDIT"
    }


    fun toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.idClearAll -> {
                deleteAllNoteDialog()
                true
            }
            R.id.idDescending -> {
                todoViewModel.getDescendingOrderListViewModel()
                todoViewModel.descendingList.observe(this, Observer {
                    todoViewModel.descendingList.observe(this, Observer {
                        adapter.setWords(it)
                        adapter.notifyDataSetChanged()
                    })
                })
                Toast.makeText(applicationContext, "Descending", Toast.LENGTH_LONG).show()
                true
            }
            R.id.idAscending -> {
                todoViewModel.getAscendingOrderListViewModel()
                todoViewModel.ascendingList.observe(this, Observer {
                    it?.let {
                        adapter.setWords(it)
                        adapter.notifyDataSetChanged()
                    }
                })
                Toast.makeText(applicationContext, "Ascending", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllNoteDialog() {
        val dialog = Dialog(this@MainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.material_dialog)
        dialog.show()
        dialog.tvDialogTitle.text = "Are you went to sure to clear all?"
        dialog.dialogNo.setOnClickListener { dialog.dismiss() }
        dialog.dialogYes.setOnClickListener {
            todoViewModel.deleteAll()
            adapter.notifyDataSetChanged()
            dialog.dismiss()
            Toast.makeText(applicationContext, "Clear All Data", Toast.LENGTH_LONG).show()
        }
    }
}