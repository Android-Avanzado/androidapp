package com.androidavanzado.cl05_minitwitter.ui.profile;

import android.Manifest;
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
import com.androidavanzado.cl05_minitwitter.retrofit.request.RequestUserProfile;
import com.androidavanzado.cl05_minitwitter.retrofit.response.ResponseUserProfile;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.single.CompositePermissionListener;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    ImageView ivAvatar;
    EditText etUsername, etEmail, etPassword, etWebsite, etDescripcion;
    Button btnSave, btnChangePassword;
    boolean loadingData = true;
    PermissionListener allPermissionsListener;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileViewModel = ViewModelProviders.of(getActivity()).get(ProfileViewModel.class);
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
            String username = etUsername.getText().toString();
            String email = etEmail.getText().toString();
            String descripcion = etDescripcion.getText().toString();
            String website = etWebsite.getText().toString();
            String password = etPassword.getText().toString();

            if(username.isEmpty()) {
                etUsername.setError("El nombre de usuario es requerido");
            } else if(email.isEmpty()) {
                etEmail.setError("El email es requerido");
            } else if(password.isEmpty()) {
                etPassword.setError("La contraseña es requerida");
            } else {
                RequestUserProfile requestUserProfile = new RequestUserProfile(username, email, descripcion, website, password);
                profileViewModel.updateProfile(requestUserProfile);
                Toast.makeText(getActivity(), "Enviando información al servidor", Toast.LENGTH_SHORT).show();
                btnSave.setEnabled(false);
            }
        });

        btnChangePassword.setOnClickListener(view -> {
            Toast.makeText(getActivity(), "Click on save", Toast.LENGTH_SHORT).show();
        });

        ivAvatar.setOnClickListener(view -> {
            // Invocar a la selección de la fotografía
            // Invocamos al método de comprobación de permisos
            checkPermissions();
        });

        // ViewModel
        profileViewModel.userProfile.observe(getActivity(), new Observer<ResponseUserProfile>() {
            @Override
            public void onChanged(@Nullable ResponseUserProfile responseUserProfile) {
                loadingData = false;
                etUsername.setText(responseUserProfile.getUsername());
                etEmail.setText(responseUserProfile.getEmail());
                etWebsite.setText(responseUserProfile.getWebsite());
                etDescripcion.setText(responseUserProfile.getDescripcion());

                if(!responseUserProfile.getPhotoUrl().isEmpty()) {
                    Glide.with(getActivity())
                            .load(Constantes.API_MINITWITTER_FILES_URL + responseUserProfile.getPhotoUrl())
                            .dontAnimate()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .centerCrop()
                            .skipMemoryCache(true)
                            .into(ivAvatar);
                }

                if(!loadingData) {
                    btnSave.setEnabled(true);
                    Toast.makeText(getActivity(), "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        profileViewModel.photoProfile.observe(getActivity(), photo -> {
                Glide.with(getActivity())
                        .load(Constantes.API_MINITWITTER_FILES_URL + photo)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .centerCrop()
                        .skipMemoryCache(true)
                        .into(ivAvatar);
        });

        return v;

    }

    private void checkPermissions() {
        PermissionListener dialogOnDeniedPermissionListener =
                DialogOnDeniedPermissionListener.Builder.withContext(getActivity())
                .withTitle("Permisos")
                .withMessage("Los permisos solicitados son necesarios para poder seleccionar una foto de perfil")
                .withButtonText("Aceptar")
                .withIcon(R.mipmap.ic_launcher)
                .build();

        allPermissionsListener = new CompositePermissionListener(
                (PermissionListener) getActivity(),
                dialogOnDeniedPermissionListener
        );

        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(allPermissionsListener)
                .check();
    }

}
