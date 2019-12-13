package com.example.sensei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
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

public class Coments extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView listaComents;
    Thread tHilo;
    int id = 0;
    ArrayList<Coments_Class> com = new ArrayList<Coments_Class>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coments);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        Log.wtf("wtf", id+"");
        tHilo = new Thread() {
            @Override
            public void run() {
                super.run();
                StringRequest request = new StringRequest(Request.Method.POST, "https://senseii.000webhostapp.com/comentarios.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Log.wtf("json", response);
                                try {
                                    JSONArray jsonTutor = new JSONArray(response);
                                    
                                    for(int i = 0 ; i < jsonTutor.length() ; i++ ){
                                        JSONObject name = jsonTutor.getJSONObject(i);
                                        String nombr = name.getString("nombre");
                                        String correo = name.getString("mensaje");
                                       // String telefono = name.getString("estrellas");
                                        Log.wtf("json 2", nombr);
                                        com.add(new Coments_Class(R.drawable.a1,nombr,correo));
                                    }
                                    relleno();
                                    //info(nombr,correo,telefono,coments);
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

    }
    public void relleno(){
        listaComents = findViewById(R.id.listComents);
        listaComents.setAdapter(new ComentsAdapter(this, R.layout.coments_layout, com));
        listaComents.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //this.finish();
    }
}