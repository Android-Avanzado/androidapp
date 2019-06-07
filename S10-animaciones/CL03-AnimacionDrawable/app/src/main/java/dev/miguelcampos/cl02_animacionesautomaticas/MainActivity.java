package dev.miguelcampos.cl02_animacionesautomaticas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView ivCheck;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivCheck = findViewById(R.id.imageViewCheck);
        ivCheck.setBackgroundResource(R.drawable.animacion_check);

        animationDrawable = (AnimationDrawable) ivCheck.getBackground();

        ivCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.start();
            }
        });

    }


}
