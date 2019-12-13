package com.example.sensei;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    Button btn;
    EditText nombre,apellido,edad,contra,insti,correo,telefono;
    int[] array;
    CheckBox calculo, fisica, quimica, programacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        calculo = findViewById(R.id.cbCalculo);
        fisica = findViewById(R.id.cbFisica);
        quimica = findViewById(R.id.cbquimica);
        programacion = findViewById(R.id.cbProgramacion);
        nombre = findViewById(R.id.etNombreA);
        apellido = findViewById(R.id.etApellidoA);
        contra = findViewById(R.id.etContraA);
        insti = findViewById(R.id.etInsti);
        correo = findViewById(R.id.etCorreoA);
        telefono = findViewById(R.id.etTelA);
        btn = findViewById(R.id.buttonRegistroA);
        array = new int[4];
        array = new int[]{-1,-1,-1,-1};
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(calculo.isChecked()){
                    String cal = calculo.getTag().toString();
                    array[0] = 1;
                }
                if(fisica.isChecked()){
                    String fis = fisica.getTag().toString();
                    array[1] = 2;
                }
                if(quimica.isChecked()){
                    array[2] = 3;
                }
                if(programacion.isChecked()){
                    array[3] = 4;
                }
               registro();
            }
        });
    }
    public void registro(){
        StringRequest request = new StringRequest(Request.Method.POST, "https://senseii.000webhostapp.com/registro.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("1")){
                            Toast.makeText(getApplicationContext(),"usuario añadido de forma exitosa", Toast.LENGTH_SHORT).show();
                            finish();
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
                for(int i = 0 ; i < 4 ; i++){
                    String add = "con"+i+"";
                    if(array[i] != 0) {
                        params.put(add, ""+array[i]);
                        Log.wtf("array "+i , add + "  "+ array[i]);
                    }
                }
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}