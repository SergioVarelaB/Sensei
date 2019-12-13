package com.example.sensei;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class Imagenes_Adapter extends ArrayAdapter<imagen_class> {
    Context context;
    int resource;
    imagen_class[] seleccionar;
    public Imagenes_Adapter(Context context, int resource, imagen_class[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.seleccionar = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if(convertView == null){
            //Crear nuestro layout que representa una fila en la lista
            //INFLATER
            LayoutInflater lInflator = ((Activity) context).getLayoutInflater();
            convertView = lInflator.inflate(resource, parent, false);
        }
        imageView = convertView.findViewById(R.id.ivImagenPer);
        imageView.setImageResource(seleccionar[position].getImagen());
        return convertView;
    }
}