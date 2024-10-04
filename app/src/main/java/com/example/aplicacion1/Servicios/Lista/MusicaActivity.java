package com.example.aplicacion1.Servicios.Lista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion1.R;
import com.example.aplicacion1.Servicios.adapter.Adapter;
import com.example.aplicacion1.Servicios.Productos;
import com.example.aplicacion1.Servicios.ServiciosFragment;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MusicaActivity extends AppCompatActivity {

    private Button botonVolver;

    RecyclerView listaMusica;
    List<Productos> productos;

    Adapter adapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musica);

        botonVolver = (Button) findViewById(R.id.botonVolver);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MusicaActivity.this, ServiciosFragment.class);
                startActivity(intent);
                finish();
            }
        });

        listaMusica = findViewById(R.id.listaMusica);
        // Inicializar Firebase Firestore
        db = FirebaseFirestore.getInstance();
        // Configurar el RecyclerView
        listaMusica.setLayoutManager(new LinearLayoutManager(this));
        productos = new ArrayList<>();
        adapter = new Adapter(this, productos);
        listaMusica.setAdapter(adapter);
        // Obtener los datos de Firestore
        obtenerDatosFirestore();


    }

    private void obtenerDatosFirestore() {
        // Obtener la referencia a la colección de invitados en Firestore
        db.collection("musica")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()) {
                        // Convertir cada documento en un objeto Productos y agregarlo a la lista
                        Productos producto = documentChange.getDocument().toObject(Productos.class);
                        productos.add(producto);
                    }
                    adapter.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
                })
                .addOnFailureListener(e -> {
                    // Manejar errores
                });
    }
}
