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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }
    public void conocimientos(View v){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.conocimientos_layout);
        //vincular los widgets
        //final EditText etName;
        //Button btn;

        //etName = dialog.findViewById(R.id.etName);
        //btn = dialog.findViewById(R.id.btn);

        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cadena = etName.getText().toString();
                Toast.makeText(getApplicationContext(),cadena,Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });*/
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();

    }
}
