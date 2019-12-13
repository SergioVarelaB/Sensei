package com.example.sensei;

public class Tutores_Class {
        private int id;
        private String telefono;
        private String correo;
        private int imagen;
        private String nombre;
        private int comentarios;
        private int estrellas;
        private String conocimientos;

        public Tutores_Class(){
            id = 0;
            telefono = "123456789";
            correo = "exampl@tec2.com";
            imagen = R.drawable.a2;
            nombre = "Juan";
            comentarios = 5;
            estrellas = 5;
            conocimientos = "";
        }

        public Tutores_Class(int id, String telefono, String correo, int imagen, String nombre, int comentarios, int estrellas, String conocimientos) {
            this.id = id;
            this.telefono = telefono;
            this.correo = correo;
            this.imagen = imagen;
            this.nombre = nombre;
            this.comentarios = comentarios;
            this.estrellas = estrellas;
            this.conocimientos = conocimientos;
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
        public int getComentarios() {
            return comentarios;
        }
        public void setComentarios(int comentarios) {
            this.comentarios = comentarios;
        }

        public int getEstrellas() {
            return estrellas;
        }

        public void setEstrellas(int estrellas) {
            this.estrellas = estrellas;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getConocimientos() {
            return conocimientos;
        }

        public void setConocimientos(String conocimientos) {
            this.conocimientos = conocimientos;
    }
}