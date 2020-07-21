package com.mustaq.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddToDoActivity::class.java))
        }


    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: $TAG")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: $TAG")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart: $TAG")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: $TAG")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: $TAG")
    }

    companion object {
        const val TAG = "MAINACTIVITY"
    }
}