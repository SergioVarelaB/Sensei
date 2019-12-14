package com.example.sensei;

public class Notificaciones_Class {
    private int imagen;
    private String nombre;
    private String comentario;

    public Notificaciones_Class() {
        imagen = R.drawable.a1;
        nombre = "Juan";
        comentario = "contacto";
    }

    public Notificaciones_Class(int imagen, String nombre, String comentario) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.comentario = comentario;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return comentario;
    }

    public void setDescripcion(String descripcion) {
        this.comentario = comentario;
    }
}

