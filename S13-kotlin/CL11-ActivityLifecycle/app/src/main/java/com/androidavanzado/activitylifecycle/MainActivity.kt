package com.androidavanzado.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("MainActivity", "método onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "método onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "método onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "método onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "método onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "método onDestroy")
    }
}
