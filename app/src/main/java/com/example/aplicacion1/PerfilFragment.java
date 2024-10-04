package com.example.aplicacion1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aplicacion1.LoginActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PerfilFragment extends Fragment {

    ImageView imageView;
    FloatingActionButton btnPhoto;
    FloatingActionButton btnCerrarSesion;

    EditText etDescripcion;
    CheckBox cbVestuario;
    CheckBox cbTarta;
    CheckBox cbLugar;
    CheckBox cbInvitados;
    CheckBox cbFlores;
    CheckBox cbInvitaciones;
    CheckBox cbBelleza;
    CheckBox cbMusica;
    CheckBox cbDulces;
    CheckBox cbDecoracion;
    CheckBox cbTatuajes;
    CheckBox cbFotografo;
    CheckBox cbCaterin;

    private String imageUrl;

    public PerfilFragment() {
        // Required empty public constructor
    }

    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        imageView = view.findViewById(R.id.imageView);
        btnPhoto = view.findViewById(R.id.btnPhoto);
        btnCerrarSesion = view.findViewById(R.id.btnCerrarSesion);
        etDescripcion = view.findViewById(R.id.etDescripcion);
        cbVestuario = view.findViewById(R.id.cbVestuario);
        cbTarta = view.findViewById(R.id.cbTarta);
        cbLugar = view.findViewById(R.id.cbLugar);
        cbInvitados = view.findViewById(R.id.cbInvitados);
        cbFlores = view.findViewById(R.id.cbFlores);
        cbInvitaciones = view.findViewById(R.id.cbInvitaciones);
        cbBelleza = view.findViewById(R.id.cbBelleza);
        cbMusica = view.findViewById(R.id.cbMusica);
        cbDulces = view.findViewById(R.id.cbDulces);
        cbDecoracion = view.findViewById(R.id.cbDecoracion);
        cbTatuajes = view.findViewById(R.id.cbTatuajes);
        cbFotografo = view.findViewById(R.id.cbFotografo);
        cbCaterin = view.findViewById(R.id.cbCaterin);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(PerfilFragment.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();
            }
        });

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });

        // Listeners para los checkboxes
        etDescripcion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                guardarDatosEnFirestore();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        cbVestuario.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                guardarDatosEnFirestore();
            }
        });

        cbTarta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                guardarDatosEnFirestore();
            }
        });

        cbLugar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                guardarDatosEnFirestore();
            }
        });

        cbInvitados.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                guardarDatosEnFirestore();
            }
        });

        cbFlores.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                guardarDatosEnFirestore();
            }
        });

        cbInvitaciones.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                guardarDatosEnFirestore();
            }
        });

        cbBelleza.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                guardarDatosEnFirestore();
            }
        });

        cbMusica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                guardarDatosEnFirestore();
            }
        });

        cbDulces.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                guardarDatosEnFirestore();
            }
        });

        cbDecoracion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                guardarDatosEnFirestore();
            }
        });

        cbTatuajes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                guardarDatosEnFirestore();
            }
        });

        cbFotografo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                guardarDatosEnFirestore();
            }
        });

        cbCaterin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                guardarDatosEnFirestore();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
            imageUrl = uri.toString();
            guardarDatosEnFirestore();
        }
    }

    private void cerrarSesion() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(requireContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show();
        // Redirigir al usuario a la pantalla de inicio de sesión
        Intent intent = new Intent(requireContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Limpiar la pila de actividades
        startActivity(intent);
    }

    private void guardarDatosEnFirestore() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Map<String, Object> userData = new HashMap<>();
            userData.put("descripcion", etDescripcion.getText().toString());
            userData.put("vestuario", cbVestuario.isChecked());
            userData.put("tarta", cbTarta.isChecked());
            userData.put("lugar", cbLugar.isChecked());
            userData.put("invitados", cbInvitados.isChecked());
            userData.put("flores", cbFlores.isChecked());
            userData.put("invitaciones", cbInvitaciones.isChecked());
            userData.put("belleza", cbBelleza.isChecked());
            userData.put("musica", cbMusica.isChecked());
            userData.put("dulces", cbDulces.isChecked());
            userData.put("decoracion", cbDecoracion.isChecked());
            userData.put("tatuajes", cbTatuajes.isChecked());
            userData.put("fotografo", cbFotografo.isChecked());
            userData.put("caterin", cbCaterin.isChecked());
            userData.put("imageUrl", imageUrl);

            db.collection("perfil").document(uid)
                    .set(userData)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getContext(), "Datos guardados en Firestore", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Error al guardar los datos en Firestore", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // Manejar el caso en el que el usuario no está autenticado
            Toast.makeText(getContext(), "Usuario no autenticado", Toast.LENGTH_SHORT).show();
            // Redirigir al usuario a la pantalla de inicio de sesión si es necesario
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
