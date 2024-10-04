package com.example.aplicacion1.Invitados;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacion1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    private static final String TAG = "AddActivity";

    EditText nombre, apellido, invitado, mesa;
    Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nombre = findViewById(R.id.txt_nombreupdate);
        apellido = findViewById(R.id.txtApellido_update);
        invitado = findViewById(R.id.txtInvitado_update);
        mesa = findViewById(R.id.txtMesaupdate);

        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Add button clicked");
                insertData();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Back button clicked");
                finish();
            }
        });
    }

    private void insertData() {
        String nombreValue = nombre.getText().toString().trim();
        String apellidoValue = apellido.getText().toString().trim();
        String invitadoValue = invitado.getText().toString().trim();
        String mesaValue = mesa.getText().toString().trim();

        if (nombreValue.isEmpty() || apellidoValue.isEmpty() || invitadoValue.isEmpty() || mesaValue.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombreValue);
        map.put("apellido", apellidoValue);
        map.put("invitado", invitadoValue);
        map.put("mesa", mesaValue);

        FirebaseDatabase.getInstance().getReference().child("invitados").push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Datos insertados exitosamente");
                        Toast.makeText(AddActivity.this, "Datos insertados", Toast.LENGTH_SHORT).show();
                        clearAll();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error al insertar datos", e);
                        Toast.makeText(AddActivity.this, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll() {
        nombre.setText("");
        apellido.setText("");
        invitado.setText("");
        mesa.setText("");
    }
}



