package com.androidgames.tictactoe.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.androidgames.tictactoe.R;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin, btnRegistro;
    private ScrollView formLogin;
    private ProgressBar pbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        formLogin = findViewById(R.id.formLogin);
        pbLogin = findViewById(R.id.progressBarLogin);
        btnRegistro = findViewById(R.id.buttonRegistro);

        changeLoginFormVisibility(true);
        eventos();
    }

    private void eventos() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if(email.isEmpty()) {
                    etEmail.setError("El email es obligatorio");
                } else if(password.isEmpty()) {
                    etPassword.setError("La contraseña es obligatoria");
                } else {
                    //TODO: realizar login en Firebase Auth
                    changeLoginFormVisibility(false);
                }

            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(i);

            }
        });
    }

    private void changeLoginFormVisibility(boolean showForm) {
        pbLogin.setVisibility(showForm ? View.GONE : View.VISIBLE);
        formLogin.setVisibility(showForm ? View.VISIBLE : View.GONE);
    }
}
