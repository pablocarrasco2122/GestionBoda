package com.example.aplicacion1.Servicios;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion1.R;
import com.example.aplicacion1.Servicios.adapter.CarritoAdapter;
import com.example.aplicacion1.Servicios.communication.CarritoCommunication;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CarritoActivity extends AppCompatActivity implements CarritoCommunication {


    private RecyclerView listaCompra;

    private CarritoAdapter adapter;

    private ArrayList<Carrito> carritoProductos;

    private TextView TotalPrecio;
    private Button bttnVolver;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_carrito);


        listaCompra = findViewById(R.id.carrito_lista);
        TotalPrecio = findViewById(R.id.precio_total);

        bttnVolver = (Button) findViewById(R.id.bttnVolver);

        bttnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarritoActivity.this, ServiciosFragment.class);
                startActivity(intent);
                finish();
            }
        });

        listaCompra.setLayoutManager(new LinearLayoutManager(this));

        carritoProductos = new ArrayList<>();
        adapter = new CarritoAdapter(this, carritoProductos,this);
        listaCompra.setAdapter(adapter);

        DatabaseReference carritoRef = FirebaseDatabase.getInstance().getReference().child("carrito");

        carritoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                carritoProductos.clear();

                for (DataSnapshot carritoSnapshot : snapshot.getChildren()) {
                    Carrito carrito = carritoSnapshot.getValue(Carrito.class);
                    carritoProductos.add(carrito);
                }
                adapter.notifyDataSetChanged();
                actualizarTotal();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error al obtener los datos del carrito", error.toException());
            }

        });

    }

    @Override
    public void eliminarDetalle(String nombre) {
        for (int i = 0; i < carritoProductos.size(); i++) {
            if (carritoProductos.get(i).getNombre().equals(nombre)) {
                // Obtén una referencia al producto en la base de datos de Firebase
                DatabaseReference carritoRef = FirebaseDatabase.getInstance().getReference().child("carrito").child(nombre);

                // Elimina el producto de la base de datos de Firebase
                int finalI = i;
                carritoRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // El producto se eliminó correctamente de la base de datos de Firebase

                        // Elimina el producto de carritoProductos
                        carritoProductos.remove(finalI);

                        // Notifica al adaptador que los datos han cambiado
                        adapter.notifyItemRemoved(finalI);

                        // Actualiza el total
                        actualizarTotal();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Hubo un error al eliminar el producto de la base de datos de Firebase
                        Log.e("CarritoActivity", "Error al eliminar el producto del carrito", e);
                    }
                });

                break;
            }
        }
    }


    public void actualizarTotal() {
        double total = 0;
        for (Carrito carritoItem : carritoProductos) {
            try {
                total += Double.parseDouble(carritoItem.getPrecio());
            } catch (NumberFormatException e) {
                Log.e("CarritoActivity", "Error al convertir el precio a double", e);
            }
        }
        if (TotalPrecio != null) {
            TotalPrecio.setText(String.format("%.2f", total)); // Actualiza el TextView con el total
        } else {
            Log.e("CarritoActivity", "TotalPrecio es null");
        }
    }

}
