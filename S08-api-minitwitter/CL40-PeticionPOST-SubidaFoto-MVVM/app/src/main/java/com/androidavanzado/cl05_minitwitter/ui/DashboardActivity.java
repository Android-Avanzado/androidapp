package com.androidavanzado.cl05_minitwitter.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.androidavanzado.cl05_minitwitter.R;
import com.androidavanzado.cl05_minitwitter.common.Constantes;
import com.androidavanzado.cl05_minitwitter.common.SharedPreferencesManager;
import com.androidavanzado.cl05_minitwitter.ui.profile.ProfileFragment;
import com.androidavanzado.cl05_minitwitter.ui.tweets.NuevoTweetDialogFragment;
import com.androidavanzado.cl05_minitwitter.ui.tweets.TweetListFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DashboardActivity extends AppCompatActivity {
    FloatingActionButton fab;
    ImageView ivAvatar;

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

    }

}
