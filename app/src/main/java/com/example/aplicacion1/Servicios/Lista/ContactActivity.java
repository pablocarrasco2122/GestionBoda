package com.example.aplicacion1.Servicios.Lista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion1.R;
import com.example.aplicacion1.Servicios.ServiciosFragment;

public class ContactActivity extends AppCompatActivity {
    private Button botonVolver, bttnEnviar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        botonVolver = (Button) findViewById(R.id.botonVolver);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactActivity.this, ServiciosFragment.class);
                startActivity(intent);
            }
        });

        bttnEnviar = (Button) findViewById(R.id.bttnEnviar);
        bttnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtenemos los datos para el envío del correo
                TextView txtCorreo = (android.widget.TextView) findViewById(R.id.txtCorreo);
                EditText editAsunto = (EditText) findViewById(R.id.editAsunto);
                EditText editMensaje = (EditText) findViewById(R.id.editMensaje);
                //Intent que levanta la actividad deseada
                Intent itSend = new Intent (android.content.Intent.ACTION_SEND);
                //Coloca los datos para el envio
                itSend.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{txtCorreo.getText().toString()});
                itSend.putExtra(android.content.Intent.EXTRA_SUBJECT,editAsunto.getText().toString());
                itSend.putExtra(android.content.Intent.EXTRA_TEXT, editMensaje.getText());
                //inicia la actividad
                startActivity(itSend);
            }
        });
    }
}
