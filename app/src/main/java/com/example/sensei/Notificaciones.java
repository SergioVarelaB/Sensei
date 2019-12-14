package com.example.sensei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

public class Notificaciones extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView listaNoti;
    Thread tHilo;
    int id = 0;
    String mensaje, nombre;
    ArrayList<Notificaciones_Class> com = new ArrayList<Notificaciones_Class>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        Log.wtf("wtf", id+"");
        //peticion de las notificaciones a la base de datos
        tHilo = new Thread() {
            @Override
            public void run() {
                super.run();
                StringRequest request = new StringRequest(Request.Method.POST, "https://senseii.000webhostapp.com/noti.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Log.wtf("json", response);
                                try {
                                    JSONArray jsonTutor = new JSONArray(response);
                                    for(int i = 0 ; i < jsonTutor.length() ; i++ ){
                                        JSONObject name = jsonTutor.getJSONObject(i);
                                        nombre = name.getString("nombre");
                                        mensaje = name.getString("telefono");
                                        Log.wtf("json 2", nombre);
                                        com.add(new Notificaciones_Class(R.drawable.a1,nombre,mensaje));
                                    }
                                    relleno();
                                    //info(nombr,correo,telefono,coments);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //Toast.makeText(getApplicationContext(), "este es el id " + id, Toast.LENGTH_SHORT).show();
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
    //llenado de la interfaz grafica
    public void relleno(){
        listaNoti = findViewById(R.id.listNoti);
        listaNoti.setAdapter(new NotificacionesAdapter(this, R.layout.coments_layout, com));
        listaNoti.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //this.finish();
        Uri uri = Uri.parse("smsto:" + mensaje);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        String text = "Hola "+ nombre + ", quiero ponerme en contacto contigo";
        i.putExtra(Intent.EXTRA_TEXT, text);
        i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));
    }
}


