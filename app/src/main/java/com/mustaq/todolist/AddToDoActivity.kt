package com.mustaq.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_add_to_do.*
import kotlinx.android.synthetic.main.activity_add_to_do.view.*
import kotlin.math.log

class AddToDoActivity : AppCompatActivity() {

    lateinit var simpleTextInputLayout: TextInputLayout
    lateinit var editText: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_do)
        initView()


        val items = listOf("Material", "Design", "Components", "Android")
        val adapter = ArrayAdapter(applicationContext, R.layout.list_item, items)
        (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun initView() {
        simpleTextInputLayout = findViewById(R.id.simpleTextInputLayout)
        editText = findViewById(R.id.edSimple)

        Log.e(TAG, "initView: ${simpleTextInputLayout.editText!!.text}")
        Log.e(TAG, "initView: ${editText.text}")

        simpleTextInputLayout.addOnEditTextAttachedListener {
            // If any specific changes should be done when the edit text is attached (and
            // thus when the trailing icon is added to it), set an
            // OnEditTextAttachedListener.

            // Example: The clear text icon's visibility behavior depends on whether the
            // EditText has input present. Therefore, an OnEditTextAttachedListener is set
            // so things like editText.getText() can be called.
            //Toast.makeText(applicationContext,"${it.editText!!.text}",Toast.LENGTH_SHORT).show()//
        }
       

    }

    companion object {
        const val TAG = "AddActivity"
    }
}