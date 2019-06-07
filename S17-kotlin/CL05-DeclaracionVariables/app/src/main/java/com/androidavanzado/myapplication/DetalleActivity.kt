package com.androidavanzado.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetalleActivity : AppCompatActivity() {
    // Variables cuyo valor no puede ser modificado
    val PI: Float = 3.14f

    // Variable que permita modificar su valor
    var contador: Int? = null
    var saludo: String = "Hola"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        contador = 5
        saludo = "Hola mundo"
    }


}
