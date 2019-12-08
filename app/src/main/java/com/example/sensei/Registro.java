package com.example.sensei;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    Button btn;
    EditText nombre,apellido,edad,contra,insti,correo,telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre = findViewById(R.id.etNombre);
        apellido = findViewById(R.id.etApellido);
        contra = findViewById(R.id.etContra);
        insti = findViewById(R.id.etInsti);
        correo = findViewById(R.id.etCorreo);
        telefono = findViewById(R.id.etTel);


    }
    public void conocimientos(View v){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.conocimientos_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
        }

    public void registro(){

    }
}
