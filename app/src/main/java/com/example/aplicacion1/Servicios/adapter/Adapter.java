package com.example.aplicacion1.Servicios.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.aplicacion1.R;
import com.example.aplicacion1.Servicios.Productos;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import javax.sql.DataSource;

public class Adapter extends RecyclerView.Adapter<Adapter.ProductosviewHolder>{

    private Context context;
    List<Productos> productos;

    public Adapter(List<Productos> productos) {
        this.productos = productos;
    }

    public Adapter(Context context, List<Productos> productos) {
        this.context = context;
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductosviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.productos_item_layout, parent, false);
        ProductosviewHolder holder = new ProductosviewHolder(v);
        return holder;
    }

    // Método llamado cuando se adjunta un ViewHolder a un elemento de la lista
    @Override
    public void onBindViewHolder(@NonNull ProductosviewHolder holder, int position) {
        Productos producto = productos.get(position);
        // Aquí establece los datos del ViewHolder
        holder.productoNombre.setText(producto.getNombre());
        holder.productoDescripcion.setText(producto.getDescripcion());
        holder.productoPrecio.setText(producto.getPrecio());


        //Obtiene una referencia a Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();

        //Obtiene una referencia a la imagen del producto y luego la URL de descarga
        StorageReference storageRef = storage.getReferenceFromUrl(producto.getImagen());
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Aquí tienes la URL de descarga
                String downloadUrl = uri.toString();

                //Usa esa URL de descarga para cargar la imagen con Glide
                Glide.with(context)
                        .load(downloadUrl)
                        .into(holder.productoImagen);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Manejar cualquier error
                Log.e("Adapter", "Error al obtener la URL de descarga", exception);
            }
        });


        holder.itemView.setOnClickListener(v -> {
            // Al hacer click se añade en el carrito
            DatabaseReference carritoRef = FirebaseDatabase.getInstance().getReference().child("carrito");
            carritoRef.push().setValue(producto)
                    .addOnSuccessListener(aVoid -> Toast.makeText(context, "Producto añadido al carrito", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(context, "Error al añadir el producto al carrito", Toast.LENGTH_SHORT).show());
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductosviewHolder extends RecyclerView.ViewHolder {

        public TextView productoNombre, productoDescripcion, productoPrecio;
        public ImageView productoImagen;
        public ProductosviewHolder(@NonNull View itemView) {
            super(itemView);
            productoNombre = (TextView) itemView.findViewById(R.id.producto_nombre);
            productoDescripcion = (TextView) itemView.findViewById(R.id.producto_descripcion);
            productoPrecio = (TextView) itemView.findViewById(R.id.producto_precio);
            productoImagen = (ImageView) itemView.findViewById(R.id.producto_imagen);
        }
    }

}
