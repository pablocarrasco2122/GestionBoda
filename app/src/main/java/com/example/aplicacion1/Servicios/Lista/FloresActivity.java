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

public class FloresActivity extends AppCompatActivity {

    private Button botonVolver;

    RecyclerView listaFlores;

    List<Productos> productos;

    Adapter adapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flores);

        botonVolver = (Button) findViewById(R.id.botonVolver);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FloresActivity.this, ServiciosFragment.class);
                startActivity(intent);
                finish();
            }
        });

        listaFlores = findViewById(R.id.listaFlores);
        // Inicializar Firebase Firestore
        db = FirebaseFirestore.getInstance();
        // Configurar el RecyclerView
        listaFlores.setLayoutManager(new LinearLayoutManager(this));
        productos = new ArrayList<>();
        adapter = new Adapter(this, productos);
        listaFlores.setAdapter(adapter);
        // Obtener los datos de Firestore
        obtenerDatosFirestore();


    }

    private void obtenerDatosFirestore() {
        // Obtener la referencia a la colección de invitados en Firestore
        db.collection("flores")
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
