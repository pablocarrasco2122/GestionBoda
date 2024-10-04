package com.example.aplicacion1.Invitados;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.myViewHolder> {

    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {
        holder.nombre.setText(model.getNombre());
        holder.apellido.setText(model.getApellido());
        holder.invitadoDe.setText(model.getInvitadoDe());
        holder.mesa.setText(model.getMesa());

        final int finalPosition = position; // Crear una variable final

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.nombre.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true, 1200)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText nombre = view.findViewById(R.id.txt_nombreupdate);
                EditText apellido = view.findViewById(R.id.txtApellido_update);
                EditText invitadoDe = view.findViewById(R.id.txtInvitado_update);
                EditText mesa = view.findViewById(R.id.txtMesaupdate);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                nombre.setText(model.getNombre());
                apellido.setText(model.getApellido());
                invitadoDe.setText(model.getInvitadoDe());
                mesa.setText(model.getMesa());
                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("nombre", nombre.getText().toString());
                        map.put("apellido", apellido.getText().toString());
                        map.put("invitadoDe", invitadoDe.getText().toString());
                        map.put("mesa", mesa.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("invitados")
                                .child(getRef(finalPosition).getKey()) // Usa finalPosition en lugar de position
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.nombre.getContext(), "Datos actualizados", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.nombre.getContext(), "Error", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.nombre.getContext());
                builder.setTitle("¿Estás seguro?");
                builder.setMessage("Los datos eliminados no se pueden recuperar");

                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("invitados")
                                .child(getRef(finalPosition).getKey()) // Usa finalPosition en lugar de position
                                .removeValue();
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.nombre.getContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, apellido, invitadoDe, mesa;
        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre_mainitem);
            apellido = itemView.findViewById(R.id.apellido_mainitem);
            invitadoDe = itemView.findViewById(R.id.Invitado_mainitem); // Corregido el ID aquí
            mesa = itemView.findViewById(R.id.mesa_mainitem);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}

