package com.androidavanzado.cl05_minitwitter.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.androidavanzado.cl05_minitwitter.retrofit.request.RequestUserProfile;
import com.androidavanzado.cl05_minitwitter.retrofit.response.ResponseUserProfile;

public class ProfileViewModel extends AndroidViewModel {
    public ProfileRepository profileRepository;
    public LiveData<ResponseUserProfile> userProfile;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository();
        userProfile = profileRepository.getProfile();
    }

    public void updateProfile(RequestUserProfile requestUserProfile) {
        profileRepository.updateProfile(requestUserProfile);
    }
}
