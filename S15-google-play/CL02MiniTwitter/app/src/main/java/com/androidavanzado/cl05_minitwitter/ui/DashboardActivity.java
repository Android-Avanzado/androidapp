package com.androidavanzado.cl05_minitwitter.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidavanzado.cl05_minitwitter.R;
import com.androidavanzado.cl05_minitwitter.common.Constantes;
import com.androidavanzado.cl05_minitwitter.common.SharedPreferencesManager;
import com.androidavanzado.cl05_minitwitter.data.ProfileViewModel;
import com.androidavanzado.cl05_minitwitter.retrofit.response.ResponseUserProfile;
import com.androidavanzado.cl05_minitwitter.ui.profile.ProfileFragment;
import com.androidavanzado.cl05_minitwitter.ui.tweets.NuevoTweetDialogFragment;
import com.androidavanzado.cl05_minitwitter.ui.tweets.TweetListFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class DashboardActivity extends AppCompatActivity implements PermissionListener {
    FloatingActionButton fab;
    ImageView ivAvatar;
    ProfileViewModel profileViewModel;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment f = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    f = TweetListFragment.newInstance(Constantes.TWEET_LIST_ALL);
                    fab.show();
                break;
            case R.id.navigation_tweets_like:
                    f = TweetListFragment.newInstance(Constantes.TWEET_LIST_FAVS);
                    fab.hide();
                break;
            case R.id.navigation_profile:
                f = new ProfileFragment();
                fab.hide();
            break;
        }

        if(f != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, f)
                    .commit();
            return true;
        }


            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        fab = findViewById(R.id.fab);
        ivAvatar = findViewById(R.id.imageViewToolbarPhoto);

        getSupportActionBar().hide();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, TweetListFragment.newInstance(Constantes.TWEET_LIST_ALL))
                .commit();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NuevoTweetDialogFragment dialog = new NuevoTweetDialogFragment();
                dialog.show(getSupportFragmentManager(), "NuevoTweetDialogFragment");
            }
        });

        // Seteamos la imagen del usuario de perfil
        String photoUrl = SharedPreferencesManager.getSomeStringValue(Constantes.PREF_PHOTOURL);
        if(!photoUrl.isEmpty()) {
            Glide.with(this)
                    .load(Constantes.API_MINITWITTER_FILES_URL + photoUrl)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .skipMemoryCache(true)
                    .into(ivAvatar);
        }

        profileViewModel.photoProfile.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String photo) {
                Glide.with(DashboardActivity.this)
                        .load(Constantes.API_MINITWITTER_FILES_URL + photo)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .centerCrop()
                        .skipMemoryCache(true)
                        .into(ivAvatar);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode != RESULT_CANCELED) {
            if(requestCode == Constantes.SELECT_PHOTO_GALLERY) {
                if(data != null) {
                    Uri imagenSeleccionada = data.getData(); // content://gallery/photos/..
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(imagenSeleccionada,
                            filePathColumn, null, null, null);
                    if(cursor != null) {
                        cursor.moveToFirst();
                        // "filename" = filePathColumn[0]
                        int imagenIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String fotoPath = cursor.getString(imagenIndex);
                        profileViewModel.uploadPhoto(fotoPath);
                        cursor.close();
                    }

                }
            }
        }
    }

    @Override
    public void onPermissionGranted(PermissionGrantedResponse response) {
        // Invocamos la selección de fotos de la galería
        Intent seleccionarFoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(seleccionarFoto, Constantes.SELECT_PHOTO_GALLERY);
    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse response) {
        Toast.makeText(this, "No se puede seleccionar la fotografía", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

    }
}
