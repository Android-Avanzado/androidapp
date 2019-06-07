package com.androidavanzado.duckhuntproject;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidavanzado.duckhuntproject.common.Constantes;

public class GameActivity extends AppCompatActivity {
    TextView tvCounterDucks, tvTimer, tvNick;
    ImageView ivDuck;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initViewComponents();
        eventos();
    }

    private void initViewComponents() {
        tvCounterDucks = findViewById(R.id.textViewCounter);
        tvTimer = findViewById(R.id.textViewTimer);
        tvNick = findViewById(R.id.textViewNick);
        ivDuck = findViewById(R.id.imageViewDuck);

        // Cambiar tipo de fuente
        Typeface typeface = Typeface.createFromAsset(getAssets(), "pixel.ttf");
        tvCounterDucks.setTypeface(typeface);
        tvTimer.setTypeface(typeface);
        tvNick.setTypeface(typeface);

        // Extras: obtener nick y setear en TextView
        Bundle extras = getIntent().getExtras();
        String nick = extras.getString(Constantes.EXTRA_NICK);
        tvNick.setText(nick);
    }

    private void eventos() {
        ivDuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                tvCounterDucks.setText(String.valueOf(counter));
            }
        });
    }
}
