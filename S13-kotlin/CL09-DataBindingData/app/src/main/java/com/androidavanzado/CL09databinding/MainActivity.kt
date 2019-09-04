package com.androidavanzado.CL09databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.androidavanzado.CL09databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var usuario: Usuario = Usuario()
        usuario.nombre = "Miguel"
        usuario.email = "info@miguelcampos.dev"

        binding.user = usuario
    }
}
