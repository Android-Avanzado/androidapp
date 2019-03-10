package com.androidavanzado.cl05_minitwitter.retrofit;

import com.androidavanzado.cl05_minitwitter.retrofit.request.RequestCreateTweet;
import com.androidavanzado.cl05_minitwitter.retrofit.response.Tweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthTwitterService {

    @GET("tweets/all")
    Call<List<Tweet>> getAllTweets();

    @POST("tweets/create")
    Call<Tweet> createTweet(RequestCreateTweet requestCreateTweet);
}
