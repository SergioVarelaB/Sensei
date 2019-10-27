package com.example.sensei;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ComentsAdapter extends ArrayAdapter<Coments_Class> {
    Context context;
    int resource;
    Coments_Class[] comentarios;
    public ComentsAdapter(Context context, int resource, Coments_Class[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.comentarios = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        TextView txtNombre, txtDesc, textView;

        if(convertView == null){
            //Crear nuestro layout que representa una fila en la lista
            //INFLATER
            LayoutInflater lInflator = ((Activity) context).getLayoutInflater();
            convertView = lInflator.inflate(resource, parent, false);
        }

        imageView = convertView.findViewById(R.id.imageVieww);
        txtNombre = convertView.findViewById(R.id.nombre);
        txtDesc = convertView.findViewById(R.id.coment);

        imageView.setImageResource(comentarios[position].getImagen());
        txtNombre.setText(comentarios[position].getNombre());
        txtDesc.setText(comentarios[position].getDescripcion());

        return convertView;
    }

}
