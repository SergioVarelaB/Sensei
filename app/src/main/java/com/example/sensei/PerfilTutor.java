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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class PerfilTutor extends AppCompatActivity  {
    ImageView ivComents, ivEstrellas, ivNotifications, imagenperfil;
    Thread tHilo;
    Integer id;
    Intent in = getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_tutor);
        ivComents = findViewById(R.id.ivComents);
        ivEstrellas = findViewById(R.id.estrellas);
        ivNotifications = findViewById(R.id.ivNotifications);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        tHilo = new Thread() {
            @Override
            public void run() {
                super.run();
                StringRequest request = new StringRequest(Request.Method.POST, "https://senseii.000webhostapp.com/tutor_info.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Log.wtf("json", response);
                                try {
                                    JSONArray jsonTutor = new JSONArray(response);
                                    JSONObject name = jsonTutor.getJSONObject(0);
                                    String nombr = name.getString("nombre");
                                    String correo = name.getString("correo");
                                    String telefono = name.getString("telefono");
                                    String coments = name.getString("coments");
                                    int imagen = name.getInt("imagen");
                                    Log.wtf("este es el",R.drawable.a1+"");
                                    Log.wtf("jsonComents", coments);
                                    info(nombr,correo,telefono,coments, imagen);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(getApplicationContext(), "este es el id " + id, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) { }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("id_tutor",id+"");
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
                Intent inCom = new Intent(getApplicationContext(),Coments.class);
                inCom.putExtra("id", id);
                startActivity(inCom);
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
    public void info(String nombre, String correo, String telefono, String coments, int imagen){
        TextView nomb = findViewById(R.id.tvNombreTut);
        TextView corr = findViewById(R.id.tvCorreoTut);
        TextView tel = findViewById(R.id.tvTelTut);
        TextView com = findViewById(R.id.tvComents);
        ImageView ima = findViewById(R.id.circularImageView);
        ima.setImageResource(imagen);
        nomb.setText(nombre);
        corr.setText(correo);
        tel.setText(telefono);
        com.setText(coments);
    }
}