package com.example.aplicacion1.Servicios;

import android.view.View;

public class Productos {
    private String nombre, descripcion,precio,imagen, cantidad;

    public Productos() { }

    public Productos(String nombre, String descripcion, String precio, String imagen, String cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Productos{" +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio='" + precio + '\'' +
                ", imagen='" + imagen + '\'' +
                ", cantidad='" + cantidad + '\'' +
                '}';
    }
}

