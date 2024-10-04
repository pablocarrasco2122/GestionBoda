package com.example.aplicacion1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class NotasFragment extends Fragment {

    private EditText editTextNota;
    private Button buttonGuardarNota;
    private LinearLayout linearLayoutNotas;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el diseño del fragmento.
        View rootView = inflater.inflate(R.layout.fragment_notas, container, false);

        // Inicializar Firebase Firestore
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Obtener referencias de vistas.
        editTextNota = rootView.findViewById(R.id.editTextNota);
        buttonGuardarNota = rootView.findViewById(R.id.buttonGuardarNota);
        linearLayoutNotas = rootView.findViewById(R.id.linearLayoutNotas);

        // Configurar el clic del botón para guardar la nota.
        buttonGuardarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNota();
            }
        });

        // Cargar y mostrar todas las notas guardadas al iniciar el fragmento.
        cargarYMostrarNotas();

        // Retornar la vista del fragmento.
        return rootView;
    }

    private void guardarNota() {
        // Obtener el contenido de la nota del EditText.
        final String nota = editTextNota.getText().toString().trim();

        // Verificar si la nota está vacía.
        if (!nota.isEmpty()) {
            // Obtener el UID del usuario actual
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {
                String uid = currentUser.getUid();

                // Crear un mapa para almacenar los datos de la nota.
                final Map<String, Object> notaData = new HashMap<>();
                notaData.put("contenido", nota);
                notaData.put("userId", uid); // Asociar la nota con el UID del usuario

                // Guardar los datos en Firestore.
                db.collection("notas")
                        .add(notaData)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                mostrarMensaje("Nota guardada correctamente");
                                // Agregar la nota al LinearLayout después de guardarla.
                                agregarNotaAlLayout(nota, documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                mostrarMensaje("Error al guardar la nota");
                            }
                        });

                // Limpiar el EditText después de guardar la nota.
                editTextNota.setText("");
            } else {
                // Manejar el caso en el que el usuario no esté autenticado
            }
        } else {
            // Mostrar un mensaje de error si la nota está vacía.
            mostrarMensaje("La nota no puede estar vacía");
        }
    }

    private void cargarYMostrarNotas() {
        // Obtener el UID del usuario actual
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();

            // Consultar las notas asociadas con el UID del usuario
            db.collection("notas")
                    .whereEqualTo("userId", uid)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                            String nota = document.getString("contenido");
                            // Mostrar la nota en la interfaz de usuario
                            agregarNotaAlLayout(nota, document.getId());
                        }
                    })
                    .addOnFailureListener(e -> {
                        // Manejar errores al cargar las notas
                        mostrarMensaje("Error al cargar las notas desde Firestore");
                    });
        } else {
            // Manejar el caso en el que el usuario no esté autenticado
        }
    }

    private void agregarNotaAlLayout(final String nota, final String notaId) {
        // Crear un nuevo TextView para la nota.
        final TextView textViewNota = new TextView(getContext());
        textViewNota.setText(nota);
        textViewNota.setTextColor(getResources().getColor(android.R.color.black)); // Establecer color del texto

        // Agregar el TextView al LinearLayout.
        linearLayoutNotas.addView(textViewNota);

        // Agregar un clic para editar la nota.
        textViewNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir un cuadro de diálogo para editar o eliminar la nota.
                mostrarOpcionesNota(nota, notaId, textViewNota);
            }
        });
    }


    private void mostrarOpcionesNota(final String nota, final String notaId, final TextView textViewNota) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Selecciona una opción")
                .setItems(new CharSequence[]{"Editar", "Eliminar"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                // Editar la nota.
                                editarNota(nota, notaId, textViewNota);
                                break;
                            case 1:
                                // Eliminar la nota.
                                eliminarNota(notaId, textViewNota);
                                break;
                        }
                    }
                });
        builder.create().show();
    }

    private void editarNota(final String nota, final String notaId, final TextView textViewNota) {
        // Crear un cuadro de diálogo para editar la nota.
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Editar Nota");

        // Crear un EditText para que el usuario ingrese la nueva nota.
        final EditText editTextNuevaNota = new EditText(getContext());
        editTextNuevaNota.setText(nota); // Establecer el texto actual de la nota.
        builder.setView(editTextNuevaNota);

        // Agregar botón de "Aceptar" al diálogo.
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Obtener la nueva nota ingresada por el usuario.
                String nuevaNota = editTextNuevaNota.getText().toString().trim();

                // Actualizar el texto de la nota en el TextView.
                textViewNota.setText(nuevaNota);

                // Actualizar la nota en Firestore.
                db.collection("notas")
                        .document(notaId)
                        .update("contenido", nuevaNota)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                mostrarMensaje("Nota editada correctamente");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                mostrarMensaje("Error al editar la nota");
                            }
                        });
            }
        });

        // Agregar botón de "Cancelar" al diálogo.
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel(); // Cancelar el diálogo sin hacer ningún cambio.
            }
        });

        // Mostrar el cuadro de diálogo.
        builder.show();
    }

    private void eliminarNota(final String notaId, final TextView textViewNota) {
        // Eliminar la nota de Firestore.
        db.collection("notas")
                .document(notaId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        linearLayoutNotas.removeView(textViewNota);
                        mostrarMensaje("Nota eliminada correctamente");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mostrarMensaje("Error al eliminar la nota");
                    }
                });
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
}



