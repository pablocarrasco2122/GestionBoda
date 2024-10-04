package com.example.aplicacion1.Servicios;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.aplicacion1.R;
import com.example.aplicacion1.Servicios.Lista.BellezaActivity;
import com.example.aplicacion1.Servicios.Lista.CaterinActivity;
import com.example.aplicacion1.Servicios.Lista.ContactActivity;
import com.example.aplicacion1.Servicios.Lista.DecoracionActivity;
import com.example.aplicacion1.Servicios.Lista.FloresActivity;
import com.example.aplicacion1.Servicios.Lista.FotografosActivity;
import com.example.aplicacion1.Servicios.Lista.InvitacionesActivity;
import com.example.aplicacion1.Servicios.Lista.LugaresActivity;
import com.example.aplicacion1.Servicios.Lista.MusicaActivity;
import com.example.aplicacion1.Servicios.Lista.RegalosActivity;
import com.example.aplicacion1.Servicios.Lista.StandsActivity;
import com.example.aplicacion1.Servicios.Lista.TartasActivity;
import com.example.aplicacion1.Servicios.Lista.VestuarioActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ServiciosFragment extends Fragment {

    private FloatingActionButton fabCarrito, fabContacto;
    private ImageButton btnFlores, btnVestuario, btnTartas, btnCaterin, btnLugares, btnDecoracion, btnInvitaciones,
            btnMusica, btnStands, btnBelleza, btnFotografos, btnRegalos;



    public ServiciosFragment() {
        // Required empty public constructor
    }

    public static ServiciosFragment newInstance(String param1, String param2) {
        ServiciosFragment fragment = new ServiciosFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_servicios, container, false);

        fabCarrito = (FloatingActionButton)vista.findViewById(R.id.fabCarrito);
        fabContacto = (FloatingActionButton)vista.findViewById(R.id.fabContacto);
        btnFlores = (ImageButton) vista.findViewById(R.id.btnFlores);
        btnVestuario = (ImageButton) vista.findViewById(R.id.btnVestuario);
        btnTartas = (ImageButton) vista.findViewById(R.id.btnTartas);
        btnCaterin = (ImageButton) vista.findViewById(R.id.btnCaterin);
        btnLugares = (ImageButton) vista.findViewById(R.id.btnLugares);
        btnDecoracion = (ImageButton) vista.findViewById(R.id.btnDecoracion);
        btnInvitaciones = (ImageButton) vista.findViewById(R.id.btnInvitaciones);
        btnMusica = (ImageButton) vista.findViewById(R.id.btnMusica);
        btnStands = (ImageButton) vista.findViewById(R.id.btnStands);
        btnBelleza = (ImageButton) vista.findViewById(R.id.btnBelleza);
        btnFotografos = (ImageButton) vista.findViewById(R.id.btnFotografos);
        btnRegalos = (ImageButton) vista.findViewById(R.id.btnRegalos);

        //Boton para visualizar el carrito de compra
        fabCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CarritoActivity.class);
                startActivity(intent);
            }
        });

        //Boton para visualizar contacto
        fabContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactActivity.class);
                startActivity(intent);
            }
        });

        btnFlores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicia la segunda actividad, la pantalla de venta Flores
                Intent intent = new Intent(vista.getContext(), FloresActivity.class);
                startActivity(intent);
            }
        });

        btnVestuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicia la segunda actividad, la pantalla de venta Vestuario
                Intent intent = new Intent(vista.getContext(), VestuarioActivity.class);
                startActivity(intent);
            }
        });

        btnTartas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicia la segunda actividad, la pantalla de venta Tartas
                Intent intent = new Intent(getActivity(), TartasActivity.class);
                startActivity(intent);
            }
        });

        btnCaterin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicia la segunda actividad, la pantalla de venta Caterin
                Intent intent = new Intent(vista.getContext(), CaterinActivity.class);
                startActivity(intent);
            }
        });

        btnLugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicia la segunda actividad, la pantalla de venta Lugares
                Intent intent = new Intent(vista.getContext(), LugaresActivity.class);
                startActivity(intent);
            }
        });

        btnDecoracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicia la segunda actividad, la pantalla de venta Decoracion
                Intent intent = new Intent(vista.getContext(), DecoracionActivity.class);
                startActivity(intent);
            }
        });

        btnInvitaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicia la segunda actividad, la pantalla de venta Invitaciones
                Intent intent = new Intent(vista.getContext(), InvitacionesActivity.class);
                startActivity(intent);
            }
        });

        btnMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicia la segunda actividad, la pantalla de venta Musica
                Intent intent = new Intent(vista.getContext(), MusicaActivity.class);
                startActivity(intent);
            }
        });

        btnStands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicia la segunda actividad, la pantalla de venta Stands
                Intent intent = new Intent(vista.getContext(), StandsActivity.class);
                startActivity(intent);
            }
        });

        btnBelleza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicia la segunda actividad, la pantalla de venta Belleza
                Intent intent = new Intent(vista.getContext(), BellezaActivity.class);
                startActivity(intent);
            }
        });

        btnFotografos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicia la segunda actividad, la pantalla de venta Fotografos
                Intent intent = new Intent(vista.getContext(), FotografosActivity.class);
                startActivity(intent);
            }
        });

        btnRegalos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicia la segunda actividad, la pantalla de venta Regalos
                Intent intent = new Intent(vista.getContext(), RegalosActivity.class);
                startActivity(intent);
            }
        });

        return vista;
    }
}