package com.androidavanzado.cl05_minitwitter.ui.profile;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidavanzado.cl05_minitwitter.common.Constantes;
import com.androidavanzado.cl05_minitwitter.data.ProfileViewModel;
import com.androidavanzado.cl05_minitwitter.R;
import com.androidavanzado.cl05_minitwitter.retrofit.response.ResponseUserProfile;
import com.bumptech.glide.Glide;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    ImageView ivAvatar;
    EditText etUsername, etEmail, etPassword, etWebsite, etDescripcion;
    Button btnSave, btnChangePassword;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_fragment, container, false);

        ivAvatar = v.findViewById(R.id.imageViewAvatar);
        etUsername = v.findViewById(R.id.editTextUsername);
        etEmail = v.findViewById(R.id.editTextEmail);
        etPassword = v.findViewById(R.id.editTextCurrentPassword);
        etWebsite = v.findViewById(R.id.editTextWebsite);
        etDescripcion = v.findViewById(R.id.editTextDescripcion);
        btnSave = v.findViewById(R.id.buttonSave);
        btnChangePassword = v.findViewById(R.id.buttonChangePassword);

        // Eventos
        btnSave.setOnClickListener(view -> {
            Toast.makeText(getActivity(), "Click on save", Toast.LENGTH_SHORT).show();
        });

        btnChangePassword.setOnClickListener(view -> {
            Toast.makeText(getActivity(), "Click on save", Toast.LENGTH_SHORT).show();
        });

        // ViewModel
        profileViewModel.userProfile.observe(getActivity(), new Observer<ResponseUserProfile>() {
            @Override
            public void onChanged(@Nullable ResponseUserProfile responseUserProfile) {
                etUsername.setText(responseUserProfile.getUsername());
                etEmail.setText(responseUserProfile.getEmail());
                etWebsite.setText(responseUserProfile.getWebsite());
                etDescripcion.setText(responseUserProfile.getDescripcion());
                if(!responseUserProfile.getPhotoUrl().isEmpty()) {
                    Glide.with(getActivity())
                            .load(Constantes.API_MINITWITTER_FILES_URL + responseUserProfile.getPhotoUrl())
                            .into(ivAvatar);
                }
            }
        });

        return v;

    }

}
