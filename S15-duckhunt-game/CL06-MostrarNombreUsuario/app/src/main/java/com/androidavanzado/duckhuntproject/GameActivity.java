package com.androidavanzado.duckhuntproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.androidavanzado.duckhuntproject.common.Constantes;

public class GameActivity extends AppCompatActivity {
    TextView tvCounterDucks, tvTimer, tvNick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvCounterDucks = findViewById(R.id.textViewCounter);
        tvTimer = findViewById(R.id.textViewTimer);
        tvNick = findViewById(R.id.textViewNick);

        // Extras: obtener nick y setear en TextView
        Bundle extras = getIntent().getExtras();
        String nick = extras.getString(Constantes.EXTRA_NICK);
        tvNick.setText(nick);

    }
}
