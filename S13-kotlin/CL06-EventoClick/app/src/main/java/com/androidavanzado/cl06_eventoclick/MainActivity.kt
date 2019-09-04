package com.androidavanzado.cl06_eventoclick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonContador.setOnClickListener { view ->
                contador++
                Toast.makeText(this, "Click en contador: $contador", Toast.LENGTH_LONG).show()
        }
    }
}
