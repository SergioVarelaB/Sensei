package com.example.sensei;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
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

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    EditText name,pass;
    TextView registro;
    Button btn;
    Thread hilo;
    int id = -1;
    Switch sw;
    Boolean login = false;
    Intent servicioNoti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.etName);
        pass = findViewById(R.id.etPass);
        registro = findViewById(R.id.tvRegs);
        btn = findViewById(R.id.btnOk);
        sw = findViewById(R.id.swAlumno);
        servicioNoti = new Intent(this, MyService.class);
        sw.setOnCheckedChangeListener(this);
        //checamos el tipo de login que se va a realizar
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login){
                    login();
                }else{
                    loginAlumno();
                }

            }
        });
        //registro y alerta para tipo de registro
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog();
                //startActivity(new Intent(getApplicationContext(),Registro.class));
            }
        });
    }
    //login de tutores
    public void login(){
        StringRequest request = new StringRequest(Request.Method.POST, "https://senseii.000webhostapp.com/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    //Log.wtf("wtff", response + "");
                    id = Integer.parseInt(response);
                    if (id != -1) {
                        Intent intent = new Intent(getApplicationContext(), PerfilTutor.class);
                        intent.putExtra("id", id);

                        /*servicioNoti.putExtra("id", id);
                        startActivity(intent);
                        startService(servicioNoti);*/
                    }else{
                            Toast.makeText(getApplicationContext(),"error en la contraseña", Toast.LENGTH_SHORT).show();
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
                params.put("username",name.getText().toString());
                params.put("password",pass.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    //login de alumno
    public void loginAlumno(){
        StringRequest request = new StringRequest(Request.Method.POST, "https://senseii.000webhostapp.com/loginAlumno.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.wtf("wtff", response + "");
                        id = Integer.parseInt(response);
                        if (id != -1) {
                            Intent intent = new Intent(getApplicationContext(), Lisa_Tutores.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            //Toast.makeText(getApplicationContext(),"alumno",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"error en la contraseña", Toast.LENGTH_SHORT).show();
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
                params.put("username",name.getText().toString());
                params.put("password",pass.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    //switch de tutor
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            login = true;
        }else{
            login = false;
        }
    }

    //alerta para checar el tipo de registro
    private void alertDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("¿Quieres registrarte?");
        dialog.setMessage("¿Como alumno o como tutor?");
        dialog.setPositiveButton(
                        "Alumno",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(),RegistroAlumno.class));
                            }
                        });
        dialog.setNegativeButton(
                "Tutor",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                        startActivity(new Intent(getApplicationContext(),Registro.class));
                    }
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}