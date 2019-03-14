package com.androidavanzado.cl05_minitwitter.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.androidavanzado.cl05_minitwitter.R;
import com.androidavanzado.cl05_minitwitter.common.Constantes;
import com.androidavanzado.cl05_minitwitter.data.TweetViewModel;

public class BottomModalTweetFragment extends BottomSheetDialogFragment {

    private TweetViewModel tweetViewModel;
    private int idTweetEliminar;

    public static BottomModalTweetFragment newInstance(int idTweet) {
        BottomModalTweetFragment fragment = new BottomModalTweetFragment();
        Bundle args = new Bundle();
        args.putInt(Constantes.ARG_TWEET_ID, idTweet);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            idTweetEliminar = getArguments().getInt(Constantes.ARG_TWEET_ID);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.bottom_modal_tweet_fragment, container, false);

        final NavigationView nav = v.findViewById(R.id.navigation_view_bottom_tweet);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if( id == R.id.action_delete_tweet ) {
                    tweetViewModel.deleteTweet(idTweetEliminar);
                    getDialog().dismiss();
                    return true;
                }

                return false;
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tweetViewModel = ViewModelProviders.of(this).get(TweetViewModel.class);
    }

}
