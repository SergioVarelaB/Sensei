package com.example.sensei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lisa_Tutores extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listaComents;
    Thread tHilo;
    ArrayList<Tutores_Class> tut = new ArrayList<Tutores_Class>();
    int id = 0;
    int id_tutor = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coments);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        //Log.wtf("wtf", id+"");
        tHilo = new Thread() {
            @Override
            public void run() {
                super.run();
                StringRequest request = new StringRequest(Request.Method.POST, "https://senseii.000webhostapp.com/tutor_info.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.wtf("json", response);
                                try {
                                    JSONArray jsonTutor = new JSONArray(response);
                                    //tut.add(new Tutores_Class());
                                    for (int i = 0; i < jsonTutor.length(); i++) {
                                        JSONObject name = jsonTutor.getJSONObject(i);
                                        id_tutor = name.getInt("id_tutor");
                                        String nombr = name.getString("nombre");
                                        String telefono = name.getString("telefono");
                                        String correo = name.getString("correo");
                                        String coments = name.getString("coments");
                                        String materias = name.getString("materias");
                                        String[] array = materias.split(",");
                                        String mensaje = "";
                                        for (int k = 0; k < array.length; k++) {
                                            mensaje += "•" + array[k] + "\n";
                                            Log.wtf("array" + k, array[k]);
                                        }
                                        //JSONArray materias = name.getJSONArray("materias");
                                        int coms = Integer.parseInt(coments);
                                        Log.wtf("json 2", materias + "");
                                        tut.add(new Tutores_Class(id_tutor, telefono, correo, R.drawable.a1, nombr, coms, 5, mensaje));
                                    }
                                    relleno();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                };
                Volley.newRequestQueue(getApplicationContext()).add(request);
            }
        };
        tHilo.start();
    }

    public void relleno() {
        listaComents = findViewById(R.id.listComents);
        listaComents.setAdapter(new TutoresAdapter(this, R.layout.tutor_layout, tut));
        listaComents.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in = new Intent(this, PerfilTutAlu.class);
        in.putExtra("id_tutor", tut.get(position).getId());
        in.putExtra("name", tut.get(position).getNombre());
        in.putExtra("telefono", tut.get(position).getTelefono());
        in.putExtra("correo", tut.get(position).getCorreo());
        in.putExtra("comentarios", tut.get(position).getComentarios() + "");
        in.putExtra("conocimienos", tut.get(position).getConocimientos());
        startActivity(in);
    }


}