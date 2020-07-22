package com.mustaq.todolist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class AddToDoActivity : AppCompatActivity() {

    lateinit var edTitle: TextInputEditText
    lateinit var edPriority: TextInputEditText
    lateinit var edDescription: TextInputEditText
    lateinit var btnSaveData: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_do)
        btnSaveData = findViewById(R.id.btnSave)

        edTitle = findViewById(R.id.edTitle)
        edPriority = findViewById(R.id.edPriority)
        edDescription = findViewById(R.id.edDescription)

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
                    val toDoDataModel = ToDoDataModel(
                        null,
                        edPriority.text.toString().trim(),
                        edTitle.text.toString().trim(),
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
}

