package com.example.sensei;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class PerfilTutor extends AppCompatActivity  {
    ImageView ivComents, ivEstrellas, ivNotifications;
    TextView nombre;
    Thread tHilo;
    int id = -1;
    Intent in = new Intent();
    //Handler
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //aqui ya se puede interactuar con la interfaz grafica
            //estamos en el hilo principal prros
            String mensaje = (String) msg.obj;
            nombre.setText(mensaje);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_tutor);
        ivComents = findViewById(R.id.ivComents);
        ivEstrellas = findViewById(R.id.estrellas);
        ivNotifications = findViewById(R.id.ivNotifications);
        nombre = findViewById(R.id.tvNombreTut);
        id = in.getIntExtra("id", -1);
        tHilo = new Thread() {
            @Override
            public void run() {
                super.run();
                StringRequest request = new StringRequest(Request.Method.POST, "https://senseii.000webhostapp.com/.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Message msg = handler.obtainMessage(1,"hola");
                                //handler.dispatchMessage(msg);
                                handler.sendMessage(msg);
                                id = Integer.parseInt(response);
                                Toast.makeText(getApplicationContext(), "este es el id " + id, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) { }
                                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("id",id+"");
                        //params.put("password", pass.getText().toString());
                        return params;
                    }
                };
                Volley.newRequestQueue(getApplicationContext()).add(request);
            }
        };
        tHilo.start();
        ivNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.notifications_layout);
            }
        });
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
