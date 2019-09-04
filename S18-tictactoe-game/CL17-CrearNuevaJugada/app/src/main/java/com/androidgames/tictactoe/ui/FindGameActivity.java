package com.androidgames.tictactoe.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidgames.tictactoe.R;
import com.androidgames.tictactoe.app.Constantes;
import com.androidgames.tictactoe.model.Jugada;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class FindGameActivity extends AppCompatActivity {
    private TextView tvLoadingMessage;
    private ProgressBar progressBar;
    private ScrollView layoutProgressBar, layoutMenuJuego;
    private Button btnJugar, btnRanking;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private FirebaseUser firebaseUser;
    private String uid, jugadaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_game);

        layoutProgressBar = findViewById(R.id.layoutprogressBar);
        layoutMenuJuego = findViewById(R.id.menuJuego);
        btnJugar = findViewById(R.id.buttonJugar);
        btnRanking = findViewById(R.id.buttonRanking);

        initProgressBar();
        initFirebase();
        eventos();
    }

    private void initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseUser.getUid();
    }

    private void eventos() {
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMenuVisibility(false);
                buscarJugadaLibre();
            }
        });

        btnRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void buscarJugadaLibre() {
        tvLoadingMessage.setText("Buscando una jugada libre...");
        db.collection("jugadas")
                .whereEqualTo("jugadorDosId", "")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult().size() == 0) {
                            // No existen partidas libres, crear una nueva
                            crearNuevaJugada();
                        } else {
                            DocumentSnapshot docJugada = task.getResult().getDocuments().get(0);
                            jugadaId = docJugada.getId();
                            Jugada jugada = docJugada.toObject(Jugada.class);
                            jugada.setJugadorDosId(uid);

                            db.collection("jugadas")
                                    .document(jugadaId)
                                    .set(jugada)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            startGame();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    changeMenuVisibility(true);
                                    Toast.makeText(FindGameActivity.this, "Hubo algún error al entrar en la jugada", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

    }

    private void crearNuevaJugada() {
        tvLoadingMessage.setText("Creando una jugada nueva...");
        Jugada nuevaJugada = new Jugada(uid);

        db.collection("jugadas")
                .add(nuevaJugada)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        jugadaId = documentReference.getId();
                        // TODO tenemos creada la jugada, debemos esperar a otro jugador
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                changeMenuVisibility(true);
                Toast.makeText(FindGameActivity.this, "Error al crear la nueva jugada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startGame() {
        Intent i = new Intent(FindGameActivity.this, GameActivity.class);
        i.putExtra(Constantes.EXTRA_JUGADA_ID, jugadaId);
        startActivity(i);
    }

    private void initProgressBar() {
        tvLoadingMessage = findViewById(R.id.textViewLoading);
        progressBar = findViewById(R.id.progressBarJugadas);

        progressBar.setIndeterminate(true);
        tvLoadingMessage.setText("Cargando...");

        changeMenuVisibility(true);
    }

    private void changeMenuVisibility(boolean showMenu) {
        layoutProgressBar.setVisibility(showMenu ? View.GONE : View.VISIBLE);
        layoutMenuJuego.setVisibility(showMenu ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        changeMenuVisibility(true);
    }
}
