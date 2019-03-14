package com.androidavanzado.cl05_minitwitter.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.androidavanzado.cl05_minitwitter.retrofit.response.Tweet;

import java.util.List;

public class TweetViewModel extends AndroidViewModel {
    private TweetRepository tweetRepository;
    private LiveData<List<Tweet>> tweets;
    private LiveData<List<Tweet>> favTweets;

    public TweetViewModel(@NonNull Application application) {
        super(application);
        tweetRepository = new TweetRepository();
        tweets = tweetRepository.getAllTweets();
    }

    public LiveData<List<Tweet>> getTweets() { return tweets; }

    public LiveData<List<Tweet>> getFavTweets() {
        favTweets = tweetRepository.getFavsTweets();
        return favTweets;
    }

    public LiveData<List<Tweet>> getNewTweets() {
        tweets = tweetRepository.getAllTweets();
        return tweets;
    }

    public LiveData<List<Tweet>> getNewFavTweets() {
        getNewTweets();
        return getFavTweets();
    }

    public void insertTweet(String mensaje) {
        tweetRepository.createTweet(mensaje);
    }

    public void likeTweet(int idTweet) {
        tweetRepository.likeTweet(idTweet);
    }
}
