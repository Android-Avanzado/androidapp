package com.androidavanzado.cl09eventoclick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1: Button = findViewById(R.id.button_1)
        btn1.setOnClickListener { view ->
            Toast.makeText(this, "Click en el botón 1", Toast.LENGTH_SHORT).show()
        }

        val tv2: TextView = findViewById(R.id.textView_2)
        tv2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Toast.makeText(this, "Click en el Texto 2", Toast.LENGTH_SHORT).show()
    }
}
