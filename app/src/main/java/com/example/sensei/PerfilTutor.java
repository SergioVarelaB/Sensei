package com.example.sensei;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class PerfilTutor extends AppCompatActivity  {
    ImageView ivComents, ivEstrellas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_tutor);
        ivComents = findViewById(R.id.ivComents);
        ivEstrellas = findViewById(R.id.estrellas);
        ivComents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Coments.class));
            }
        });

    }
    public void estrellas(View v){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.estrellas_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
    }
}
