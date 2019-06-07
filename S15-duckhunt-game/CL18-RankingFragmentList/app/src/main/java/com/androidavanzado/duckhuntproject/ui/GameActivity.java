package com.androidavanzado.duckhuntproject.ui;

import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidavanzado.duckhuntproject.R;
import com.androidavanzado.duckhuntproject.common.Constantes;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    TextView tvCounterDucks, tvTimer, tvNick;
    ImageView ivDuck;
    int counter = 0;
    int anchoPantalla;
    int altoPantalla;
    Random aleatorio;
    boolean gameOver = false;
    String id, nick;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        db = FirebaseFirestore.getInstance();

        initViewComponents();
        eventos();
        initPantalla();
        moveDuck();
        initCuentaAtras();
    }

    private void initCuentaAtras() {
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                long segundosRestantes = millisUntilFinished / 1000;
                tvTimer.setText(segundosRestantes + "s");
            }

            public void onFinish() {
                tvTimer.setText("0s");
                gameOver = true;
                mostrarDialogoGameOver();
                saveResultFirestore();
            }
        }.start();
    }

    private void saveResultFirestore() {
        db.collection("users")
                .document(id)
                .update(
                  "ducks", counter
                );
    }

    private void mostrarDialogoGameOver() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Has conseguido cazar " + counter + " patos")
                .setTitle("Game over");
        builder.setCancelable(false);

        // Add the buttons
        builder.setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                counter = 0;
                tvCounterDucks.setText("0");
                gameOver = false;
                initCuentaAtras();
                moveDuck();
            }
        });
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                finish();
            }
        });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        // 4. Mostrar el diálogo
        dialog.show();
    }

    private void initPantalla() {
        // 1. Obtener el tamaño de la pantalla del dispositivo
        // en el que estamos ejecutando la app
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        anchoPantalla = size.x;
        altoPantalla = size.y;

        // 2. Inicializamos el objeto para generar números aleatorios
        aleatorio = new Random();
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
        nick = extras.getString(Constantes.EXTRA_NICK);
        id = extras.getString(Constantes.EXTRA_ID);
        tvNick.setText(nick);
    }

    private void eventos() {
        ivDuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!gameOver) {
                    counter++;
                    tvCounterDucks.setText(String.valueOf(counter));

                    ivDuck.setImageResource(R.drawable.duck_clicked);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ivDuck.setImageResource(R.drawable.duck);
                            moveDuck();
                        }
                    }, 500);
                }

            }
        });
    }

    private void moveDuck() {
        int min = 0;
        int maximoX = anchoPantalla - ivDuck.getWidth();
        int maximoY = altoPantalla - ivDuck.getHeight();

        // Generamos 2 números aleatorios, uno para la coordenada
        // x y otro para la coordenada Y.
        int randomX = aleatorio.nextInt(((maximoX - min) + 1) + min);
        int randomY = aleatorio.nextInt(((maximoY - min) + 1) + min);

        // Utilizamos los números aleatorios para mover el pato
        // a esa posición
        ivDuck.setX(randomX);
        ivDuck.setY(randomY);
    }
}
