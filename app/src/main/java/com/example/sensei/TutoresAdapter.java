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

public class TutoresAdapter extends ArrayAdapter<Tutores_Class> {
    Context context;
    int resource;
    ArrayList<Tutores_Class> tutores;
    public TutoresAdapter(Context context, int resource, ArrayList<Tutores_Class> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.tutores = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        TextView txtNombre, txtComents, texStars;

        if(convertView == null){
            //Crear nuestro layout que representa una fila en la lista
            //INFLATER
            LayoutInflater lInflator = ((Activity) context).getLayoutInflater();
            convertView = lInflator.inflate(resource, parent, false);
        }

        imageView = convertView.findViewById(R.id.ivTutor_tut);
        txtNombre = convertView.findViewById(R.id.nombre_tut);
        txtComents = convertView.findViewById(R.id.Tut_coments);
        texStars = convertView.findViewById(R.id.tutStars);

        imageView.setImageResource(tutores.get(position).getImagen());
        txtNombre.setText(tutores.get(position).getNombre());
        //txtComents.setText(tutores.get(position).getComentarios());
        //texStars.setText((tutores.get(position).getEstrellas()));
        txtComents.setText("2");
        texStars.setText("3");

        return convertView;
    }

}
