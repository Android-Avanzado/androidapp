package com.androidavanzado.cl09eventoclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.button_1);

        btn1.setOnClickListener( view -> {
            Toast.makeText(this, "Click en el botón 1", Toast.LENGTH_SHORT).show();
        });

        TextView tv2 = findViewById(R.id.textView_2);
        tv2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Click en el Texto 2", Toast.LENGTH_SHORT).show();
    }
}
