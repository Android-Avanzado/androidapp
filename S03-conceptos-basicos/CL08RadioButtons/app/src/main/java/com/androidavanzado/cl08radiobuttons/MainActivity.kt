package com.androidavanzado.cl08radiobuttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sexoSeleccionado(view: View) {
        val radioButtonSeleccionado = view as RadioButton

        when(view.id) {
            R.id.radioButtonMujer -> {
                Toast.makeText(this, "Se ha seleccionado Mujer", Toast.LENGTH_SHORT).show()
            }
            R.id.radioButtonHombre -> {
                Toast.makeText(this, "Se ha seleccionado Hombre", Toast.LENGTH_SHORT).show()
            }
            R.id.radioButtonIndefinido -> {
                Toast.makeText(this, "Se ha seleccionado Indefinido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
