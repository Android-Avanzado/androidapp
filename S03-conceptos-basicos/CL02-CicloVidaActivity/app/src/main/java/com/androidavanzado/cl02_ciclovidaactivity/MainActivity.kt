package com.androidavanzado.cl02_ciclovidaactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("LOGCICLO", "Entramos en método onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("LOGCICLO", "Entramos en método onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.i("LOGCICLO", "Entramos en método onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("LOGCICLO", "Entramos en método onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.i("LOGCICLO", "Entramos en método onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LOGCICLO", "Entramos en método onDestroy")

    }
}
