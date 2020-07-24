package com.mustaq.todolist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class AddToDoActivity : AppCompatActivity() {

    lateinit var edTitle: TextInputEditText
    lateinit var edPriority: AutoCompleteTextView
    lateinit var edDescription: TextInputEditText
    lateinit var btnSaveData: MaterialButton
    lateinit var btnUpdateData: MaterialButton
    lateinit var priorityTextInputLayout: TextInputLayout
    var priority=""
    var getIntent = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_do)
        btnSaveData = findViewById(R.id.btnSave)

        initView()
    }

    private fun initView() {

        edTitle = findViewById(R.id.edTitle)
        edPriority = findViewById(R.id.edPriority)
        edDescription = findViewById(R.id.edDescription)
        btnUpdateData = findViewById(R.id.btnupdate)
        btnSaveData = findViewById(R.id.btnSave)
        priorityTextInputLayout = findViewById(R.id.priorityTextInputLayout)
        val items = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
        for (i in 1..10) {
        }
        val adapter =
            ArrayAdapter(applicationContext, R.layout.support_simple_spinner_dropdown_item, items)
        /*(priorityTextInputLayout.edPriority as AutoCompleteTextView)?.setAdapter(adapter)*/

        val editTextFilledExposedDropdown: AutoCompleteTextView = findViewById(R.id.edPriority)
        editTextFilledExposedDropdown.setAdapter(adapter)

        editTextFilledExposedDropdown.setOnItemClickListener { parent, view, position, id ->
            Log.e(TAG, "initView: ${parent.adapter.getItem(position)}")
            priority=parent.adapter.getItem(position).toString()
        }


        btnSaveData.setOnClickListener {
            when {
                edTitle.text!!.isEmpty() -> {
                    toast(this, "Please Enter Title")
                }
                priority.isEmpty() -> {
                    toast(this, "Please Enter Priority")
                }
                edDescription.text!!.isEmpty() -> {
                    toast(this, "Please Enter Description")
                }
                else -> {
                    val toDoDataModel = TodoModel(
                        edTitle.text.toString().trim(),
                        priority.toInt(),
                        edDescription.text.toString().trim()
                    )
                    val replyIntent = Intent()
                    replyIntent.putExtra(EXTRA_REPLY, toDoDataModel)
                    this.setResult(RESULT_OK, replyIntent)
                    this.finish()
                }
            }
        }


        /*  if (intent.extras!![MainActivity.EDIT_INTENT] != null) {
              showView(btnUpdateData)
              hideView(btnSaveData)
              val editModel = intent.getParcelableExtra<TodoModel>(MainActivity.EDIT_INTENT)
              edTitle.setText(editModel.getTaskTitle())
              edPriority.setText(editModel.getTaskPriority().toString())
              edDescription.setText(editModel.getTaskDescription())
              btnUpdateData.setOnClickListener {
                  when {
                      edTitle.text!!.isEmpty() -> {
                          toast(this, "Please Enter Title")
                      }
                      edPriority.text!!.isEmpty() -> {
                          toast(this, "Please Enter Priority")
                      }
                      edDescription.text!!.isEmpty() -> {
                          toast(this, "Please Enter Description")
                      }
                      else -> {
                          val toDoDataModel = TodoModel(
                              edTitle.text.toString().trim(),
                              edPriority.text.toString().toInt(),
                              edDescription.text.toString().trim()
                          )
                          val replyIntent = Intent()
                          replyIntent.putExtra(EXTRA_REPLY, toDoDataModel)
                          this.setResult(RESULT_OK, replyIntent)
                          this.finish()
                      }
                  }
              }


          }
          else {
              hideView(btnUpdateData)
              showView(btnSaveData)
              btnSaveData.setOnClickListener {
                  when {
                      edTitle.text!!.isEmpty() -> {
                          toast(this, "Please Enter Title")
                      }
                      edPriority.text!!.isEmpty() -> {
                          toast(this, "Please Enter Priority")
                      }
                      edDescription.text!!.isEmpty() -> {
                          toast(this, "Please Enter Description")
                      }
                      else -> {
                          val toDoDataModel = TodoModel(
                              edTitle.text.toString().trim(),
                              edPriority.text.toString().toInt(),
                              edDescription.text.toString().trim()
                          )
                          val replyIntent = Intent()
                          replyIntent.putExtra(EXTRA_REPLY, toDoDataModel)
                          this.setResult(RESULT_OK, replyIntent)
                          this.finish()
                      }
                  }
              }
          }*/
    }

    companion object {
        const val TAG = "AddActivity"
        const val EXTRA_REPLY = "EXTRA_REPLAY"
    }

    fun checkIsEmpty(vararg view: View) {
        for (i in view) {

        }
    }

    fun fieldCheckEmpty(editText: TextInputEditText): Boolean {
        return editText.text!!.isEmpty()
    }

    fun toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun hideView(vararg view: View) {
        for (i in view) {
            i.visibility = View.GONE
        }
    }

    fun showView(vararg view: View) {
        for (i in view) {
            i.visibility = View.VISIBLE
        }
    }
}

