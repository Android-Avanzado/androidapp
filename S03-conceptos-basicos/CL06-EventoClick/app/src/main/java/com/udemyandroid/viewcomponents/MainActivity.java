package com.udemyandroid.viewcomponents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.buttonLogin);

        // Asignación del evento click
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // CheckBox checkBox = (CheckBox)v;
        // boolean seleccionado = checkBox.isChecked();
        Log.i("APP", "Click en login");
    }
}
