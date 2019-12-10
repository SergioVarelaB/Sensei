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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    Button btn;
    EditText nombre,apellido,edad,contra,insti,correo,telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre = findViewById(R.id.etNombreA);
        apellido = findViewById(R.id.etApellidoA);
        contra = findViewById(R.id.etContraA);
        insti = findViewById(R.id.etInsti);
        correo = findViewById(R.id.etCorreoA);
        telefono = findViewById(R.id.etTelA);
        btn = findViewById(R.id.buttonRegistroA);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               registro();
            }
        });
    }
    public void conocimientos(View v){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.conocimientos_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
        }
    public void registro(){
        StringRequest request = new StringRequest(Request.Method.POST, "https://senseii.000webhostapp.com/registro.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("1")){
                            Toast.makeText(getApplicationContext(),"usuario añadido de forma exitosa", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",nombre.getText().toString());
                params.put("apPat",apellido.getText().toString());
                params.put("tel",telefono.getText().toString());
                params.put("correo",correo.getText().toString());
                params.put("password",contra.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}
