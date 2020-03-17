package com.androidavanzado.cl07checkboxes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickOnHobby(view: View) {
        val checkBox: CheckBox = view as CheckBox
        val checked: Boolean = checkBox.isChecked

        when(view.id) {
            R.id.checkBoxDeporte -> {
                if(checked) {
                    Toast.makeText(this, "Se ha marcado Deporte", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Se ha Desmarcado Deporte", Toast.LENGTH_LONG).show()
                }
            }
            R.id.checkBoxVideojuegos -> {
                if(checked) {
                    Toast.makeText(this, "Se ha marcado Videojuegos", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(this, "Se ha Desmarcado Videojuegos", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}
