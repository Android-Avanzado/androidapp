package com.androidavanzado.cl06_eventoclick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var dado: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDado: Button = findViewById(R.id.buttonTirarDado)
        dado = findViewById(R.id.imageViewDado)

        btnDado.setOnClickListener {
            generarDadoAleatorio()
        }

    }

    private fun generarDadoAleatorio() {
        val randomInt = Random().nextInt(6) + 1

        val imageToLoad = when(randomInt) {
            1 -> R.drawable.dado_1
            2 -> R.drawable.dado_2
            3 -> R.drawable.dado_3
            4 -> R.drawable.dado_4
            5 -> R.drawable.dado_5
            else -> R.drawable.dado_6
        }

        dado.setImageResource(imageToLoad)
    }
}
