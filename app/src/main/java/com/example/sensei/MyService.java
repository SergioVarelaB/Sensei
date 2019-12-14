package com.example.sensei;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyService extends Service {

    int id;
    Thread tHilo;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        id = intent.getIntExtra("id", 0);
        tHilo = new Thread() {
            @Override
            public void run() {
                super.run();
                //Log.w("onStart: ", "Hasta aquí si llegué");
                while (true) {
                    try {
                        Thread.sleep(2500);
                        StringRequest request = new StringRequest(Request.Method.POST, "https://senseii.000webhostapp.com/noti.php",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        try {
                                            int res = Integer.parseInt(response);
                                            int i = 1;
                                            boolean flag = false;
                                            Log.wtf("respuesta ", res+"");
                                            if(res>i){
                                                //Toast.makeText(getApplicationContext(),"mensaje",Toast.LENGTH_SHORT).show();
                                               //noti();
                                               //Toast.makeText(getApplicationContext(),"esto deberia ser una notificacion",Toast.LENGTH_SHORT).show();
                                               flag = false;
                                               i++;
                                               Log.wtf("i",i+"");
                                            }
                                            //info(nombr,correo,telefono,coments, imagen);
                                        } catch (Exception e) {
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
                                return params;
                            }
                        };
                        Volley.newRequestQueue(getApplicationContext()).add(request);
                        //Log.w("prueba servicio: ", "id: " + id);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        tHilo.start();
    }
}
