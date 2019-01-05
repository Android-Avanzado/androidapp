package com.udemyandroid.ciclovidafragment;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Mensaje desde Activity", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
