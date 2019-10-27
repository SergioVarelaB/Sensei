package com.example.sensei;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Coments extends AppCompatActivity implements AdapterView.OnItemClickListener{
    Coments_Class[] comentarios = {
            new Coments_Class(),
            new Coments_Class(R.drawable.a1, "Juanito", "Buen servicio"),
            new Coments_Class(R.drawable.a2, "Jhonatan", "Mal servicio"),
            new Coments_Class(R.drawable.a3, "Pepe", "Medio regulis servicio"),
            new Coments_Class(R.drawable.a4, "Maria", "servicio"),
            new Coments_Class(R.drawable.a1, "Juanito", "Buen servicio"),
            new Coments_Class(R.drawable.a2, "Jhonatan", "Mal servicio"),
            new Coments_Class(R.drawable.a3, "Pepe", "Medio regulis servicio"),
            new Coments_Class(R.drawable.a4, "Maria", "servicio"),
            new Coments_Class(R.drawable.a1, "Juanito", "Buen servicio"),
            new Coments_Class(R.drawable.a2, "Jhonatan", "Mal servicio"),
            new Coments_Class(R.drawable.a3, "Pepe", "Medio regulis servicio"),
            new Coments_Class(R.drawable.a4, "Maria", "servicio"),

    };
    ListView listaComents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coments);
        listaComents = findViewById(R.id.listComents);
        listaComents.setAdapter(new ComentsAdapter(this, R.layout.coments_layout, comentarios));
        listaComents.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"juas juas juas" , Toast.LENGTH_LONG).show();
    }
}
