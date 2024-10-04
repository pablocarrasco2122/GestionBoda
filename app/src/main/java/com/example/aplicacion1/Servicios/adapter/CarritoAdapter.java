package com.example.aplicacion1.Servicios.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplicacion1.R;
import com.example.aplicacion1.Servicios.Carrito;
import com.example.aplicacion1.Servicios.CarritoActivity;
import com.example.aplicacion1.Servicios.Productos;
import com.example.aplicacion1.Servicios.communication.CarritoCommunication;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder> {


    private static Context context;
    private List<Carrito> carrito;
    private final CarritoCommunication c;

    public CarritoAdapter(Context context, List<Carrito> carrito, CarritoCommunication c) {
        this.context = context;
        this.carrito = carrito != null ? carrito : new ArrayList<>();
        this.c = c;
    }

    @NonNull
    @Override
    public CarritoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item_layout, parent, false);
        return new CarritoViewHolder(v,this.c);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoViewHolder holder, int position) {
        Carrito carrito = this.carrito.get(position);
        holder.productoNombre.setText(carrito.getNombre());
        holder.productoCantidad.setText(carrito.getCantidad());
        holder.productoPrecio.setText(carrito.getPrecio());

        //Eliminar item del carrito
        holder.bttnDeleteProducto.setOnClickListener(v -> {
            // Obtén el nombre del producto
            String productoNombre = this.carrito.get(holder.getAdapterPosition()).getNombre();

            // Llama al método eliminarDetalle() de CarritoActivity
            ((CarritoActivity) context).eliminarDetalle(productoNombre);
        });
    }

    @Override
    public int getItemCount() {
        return this.carrito.size();
    }

    public Carrito[] getCarrito() {
        return carrito.toArray(new Carrito[0]);
    }

    public static class CarritoViewHolder extends RecyclerView.ViewHolder {

        public TextView productoNombre, productoCantidad, productoPrecio;
        public ImageButton bttnDeleteProducto;
        private final CarritoCommunication c;

        public CarritoViewHolder(@NonNull View itemView, CarritoCommunication c) {
            super(itemView);
            productoNombre = itemView.findViewById(R.id.car_producto_nombre);
            productoCantidad = itemView.findViewById(R.id.car_producto_cantidad);
            productoPrecio = itemView.findViewById(R.id.car_producto_precio);
            bttnDeleteProducto = itemView.findViewById(R.id.bttnDeleteProducto);
            this.c = c;
        }

    }
}
