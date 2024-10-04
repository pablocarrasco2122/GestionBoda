package com.example.aplicacion1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    private EditText etNombreApellidos, etCorreo, etRepetirCorreo, etContraseña, etRepetirContraseña;
    private Button btGuardar;
    private Button btVolver;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializar Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Referenciar los elementos de la interfaz
        etNombreApellidos = findViewById(R.id.nombreapellidos);
        etCorreo = findViewById(R.id.correo);
        etRepetirCorreo = findViewById(R.id.repetircorreo);
        etContraseña = findViewById(R.id.contraseña);
        etRepetirContraseña = findViewById(R.id.repetircontraseña);
        btGuardar = findViewById(R.id.bttnGuardar);
        btVolver = findViewById(R.id.butnVolver);
        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVolver = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intentVolver);
            }
        });


        // Configurar el clic del botón de guardar
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });
    }


    private void guardarDatos() {
        String nombreApellidos = etNombreApellidos.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String repetirCorreo = etRepetirCorreo.getText().toString().trim();
        String contraseña = etContraseña.getText().toString().trim();
        String repetirContraseña = etRepetirContraseña.getText().toString().trim();

        // Verificar si algún campo está vacío
        if (nombreApellidos.isEmpty() || correo.isEmpty() || repetirCorreo.isEmpty() || contraseña.isEmpty() || repetirContraseña.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar si el correo y la repetición del correo coinciden
        if (!correo.equals(repetirCorreo)) {
            Toast.makeText(this, "Los correos no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar si la contraseña y la repetición de la contraseña coinciden
        if (!contraseña.equals(repetirContraseña)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear un mapa para almacenar los datos del usuario
        Map<String, Object> userData = new HashMap<>();
        userData.put("nombreApellidos", nombreApellidos);
        userData.put("correo", correo);
        userData.put("contraseña", contraseña);

        // Guardar los datos en Firestore
        db.collection("usuarios")
                .add(userData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegistroActivity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                        // Aquí puedes redirigir al usuario a la pantalla de inicio de sesión, por ejemplo
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistroActivity.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                        // Manejar el error, por ejemplo, mostrando un mensaje al usuario
                    }

                });

    }
}





