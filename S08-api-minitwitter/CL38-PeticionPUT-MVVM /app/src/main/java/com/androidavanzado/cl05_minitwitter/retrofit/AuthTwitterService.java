package com.androidavanzado.cl05_minitwitter.retrofit;

import com.androidavanzado.cl05_minitwitter.retrofit.request.RequestCreateTweet;
import com.androidavanzado.cl05_minitwitter.retrofit.request.RequestUserProfile;
import com.androidavanzado.cl05_minitwitter.retrofit.response.ResponseUserProfile;
import com.androidavanzado.cl05_minitwitter.retrofit.response.Tweet;
import com.androidavanzado.cl05_minitwitter.retrofit.response.TweetDeleted;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AuthTwitterService {

    // Tweets
    @GET("tweets/all")
    Call<List<Tweet>> getAllTweets();

    @POST("tweets/create")
    Call<Tweet> createTweet(@Body RequestCreateTweet requestCreateTweet);

    @POST("tweets/like/{idTweet}")
    Call<Tweet> likeTweet(@Path("idTweet") int idTweet);

    @DELETE("tweets/{idTweet}")
    Call<TweetDeleted> deleteTweet(@Path("idTweet") int idTweet);

    // Users
    @GET("users/profile")
    Call<ResponseUserProfile> getProfile();

    @PUT("users/profile")
    Call<ResponseUserProfile> updateProfile(@Body RequestUserProfile requestUserProfile);

}
