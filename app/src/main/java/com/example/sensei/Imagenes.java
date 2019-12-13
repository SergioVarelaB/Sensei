package com.example.sensei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Imagenes extends AppCompatActivity implements AdapterView.OnItemClickListener {
    int hola = 5;
    //tdo esto parqa seleccionar una imagen de un activity
    imagen_class[] seleccionar = {
            new imagen_class(),
            new imagen_class(R.drawable.a2),
            new imagen_class(R.drawable.a3),
            new imagen_class(R.drawable.a4),
    };
    ListView listaImagenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes);
        listaImagenes = findViewById(R.id.listaImagenes);
        listaImagenes.setAdapter(new Imagenes_Adapter(this, R.layout.img_layout, seleccionar));
        listaImagenes.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, seleccionar[position].getImagen(), Toast.LENGTH_SHORT).show();
        int imagen = seleccionar[position].getImagen();
        Intent intent = new Intent();
        intent.putExtra("imagen", seleccionar[position].getImagen());
        setResult(RESULT_OK, intent);
        finish();
    }
}
