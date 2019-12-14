package com.example.sensei;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificacionesAdapter extends ArrayAdapter<Notificaciones_Class> {
    Context context;
    int resource;
    ArrayList<Notificaciones_Class> comentarios;
    public NotificacionesAdapter(Context context, int resource, ArrayList<Notificaciones_Class> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.comentarios = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        TextView txtNombre, txtDesc;

        if(convertView == null){
            //Crear nuestro layout que representa una fila en la lista
            //INFLATER
            LayoutInflater lInflator = ((Activity) context).getLayoutInflater();
            convertView = lInflator.inflate(resource, parent, false);
        }

        imageView = convertView.findViewById(R.id.imageVieww);
        txtNombre = convertView.findViewById(R.id.nombre);
        txtDesc = convertView.findViewById(R.id.coment);

        imageView.setImageResource(comentarios.get(position).getImagen());
        txtNombre.setText(comentarios.get(position).getNombre());
        txtDesc.setText(comentarios.get(position).getDescripcion());
        return convertView;
    }

}