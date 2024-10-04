package com.example.aplicacion1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.fragment.app.Fragment;

public class InspiracionFragment extends Fragment {

    private WebView visorWeb;
    private String url = "https://bodaperfectapp.blogspot.com/";

    public InspiracionFragment() {
        // Constructor vacío requerido.
    }

    public static InspiracionFragment newInstance(String param1, String param2) {
        InspiracionFragment fragment = new InspiracionFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento.
        View rootView = inflater.inflate(R.layout.fragment_inspiracion, container, false);

        // Obtener referencia del WebView.
        visorWeb = rootView.findViewById(R.id.visorWeb);

        // Configurar ajustes para el WebView.
        WebSettings ajustesVisorWeb = visorWeb.getSettings();
        ajustesVisorWeb.setJavaScriptEnabled(true);

        // Configurar un WebViewClient para manejar la navegación dentro del WebView.
        visorWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url); // Cargar la URL dentro del WebView.
                return false; // Devolver false para que el WebView no intente abrir el navegador externo.
            }
        });

        // Cargar la URL en el WebView.
        visorWeb.loadUrl(url);

        // Retornar la vista del fragmento.
        return rootView;
    }
}
