package com.example.sensei;

public class Tutores_Class {
        private int imagen;
        private String nombre;
        private int comentarios;
        private int estrellas;

        public Tutores_Class(){
            imagen = R.drawable.a2;
            nombre = "Juan";
            comentarios = 5;
            estrellas = 5;
        }

        public Tutores_Class(int imagen, String nombre, int comentarios, int estrellas) {
            this.imagen = imagen;
            this.nombre = nombre;
            this.comentarios = comentarios;
            this.estrellas = estrellas;
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
}