package com.example.sensei;

public class imagen_class {
    private int img;

    public imagen_class(){
        img = R.drawable.a1;
    }
    public imagen_class(int imagen) {
        this.img = imagen;
    }
    public int getImagen() {
        return img;
    }

    public void setImagen(int imagen) {
        this.img = imagen;
    }
}