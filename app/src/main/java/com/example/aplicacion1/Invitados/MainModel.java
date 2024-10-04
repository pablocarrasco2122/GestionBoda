package com.example.aplicacion1.Invitados;

public class MainModel {
    private String nombre;
    private String apellido;
    private String invitadoDe;
    private String mesa;

    // Constructor por defecto necesario para llamadas a DataSnapshot.getValue(MainModel.class)
    public MainModel() {
    }

    // Constructor para inicializar todos los campos
    public MainModel(String nombre, String apellido, String invitadoDe, String mesa) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.invitadoDe = invitadoDe;
        this.mesa = mesa;
    }

    // Métodos getter y setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos getter y setter para apellido
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Métodos getter y setter para invitadoDe
    public String getInvitadoDe() {
        return invitadoDe;
    }

    public void setInvitadoDe(String invitadoDe) {
        this.invitadoDe = invitadoDe;
    }

    // Métodos getter y setter para mesa
    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }
}


