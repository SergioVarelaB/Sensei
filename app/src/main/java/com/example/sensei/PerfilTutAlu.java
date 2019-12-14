package com.example.sensei;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.transition.CircularPropagation;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class PerfilTutAlu extends AppCompatActivity {
    Intent in = getIntent();
    TextView tvName, tvTelefon, tvCorreo, tvComents, tvConocimientos;
    String telefono, correo, name, comentarios, conocimientos;
    ImageView ivPhono, ivMail, ivComents, circularImageView2;
    Button btnSol;
    int ima = R.drawable.a4;
    int id = -1;
    int id_alu = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_tut_alu);
        Intent intent = getIntent();
        id_alu = intent.getIntExtra("id_alu",0);
        id = intent.getIntExtra("id_tutor", 0);
        name = intent.getStringExtra("name");
        telefono = intent.getStringExtra("telefono");
        correo = intent.getStringExtra("correo");
        comentarios = intent.getStringExtra("comentarios");
        conocimientos = intent.getStringExtra("conocimienos");
        ima = intent.getIntExtra("imagen",0);
        ivPhono = findViewById(R.id.ivMarcar);
        tvName = findViewById(R.id.tvNombreTut2);
        ivComents = findViewById(R.id.ivComents2);
        btnSol = findViewById(R.id.buttonSol);
        circularImageView2 = findViewById(R.id.circularImageView2);
        circularImageView2.setImageResource(ima);
        tvConocimientos = findViewById(R.id.tvConoc);
        tvConocimientos.setText(conocimientos);
        ivMail = findViewById(R.id.ivMail);
        tvName.setText(name);
        tvComents = findViewById(R.id.tvComents2);
        tvComents.setText(comentarios);
        tvTelefon = findViewById(R.id.tvTelTut2);
        tvTelefon.setText(telefono);
        tvCorreo = findViewById(R.id.tvCorreoTut2);
        tvCorreo.setText(correo);
        Log.wtf("wtf", name);
        ivComents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inCom = new Intent(getApplicationContext(),Coments.class);
                inCom.putExtra("id", id);
                startActivity(inCom);
            }
        });
        ivMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail();
            }
        });
        ivPhono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial();
            }
        });
        btnSol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitar();
            }
        });
        //int id = in.getIntExtra("id_tutor",-1);
        //Toast.makeText(this,""+id,Toast.LENGTH_SHORT).show();
    }
    //metodo para llamar al tutor con el numero registrado del tutor
    public void dial(){
        Intent in = new Intent(Intent.ACTION_DIAL);
        in.setData(Uri.parse("tel:" + telefono));
        Toast.makeText(getApplicationContext(),"ss",Toast.LENGTH_SHORT).show();
        startActivity(in);
    }
    //metodo para mmandar mail al tutor
    public void mail(){
        String mensaje = "Hola "+ name + ", quiero ponerme en contacto contigo";
        Intent in = new Intent(Intent.ACTION_SEND);
        in.putExtra(Intent.EXTRA_EMAIL  , new String[]{correo});
        in.putExtra(Intent.EXTRA_SUBJECT,"Sensei app");
        in.putExtra(Intent.EXTRA_TEXT,mensaje);
        in.setType("message/rfc822");
        //Toast.makeText(getApplicationContext(),"ss",Toast.LENGTH_SHORT).show();
        startActivity(Intent.createChooser(in,"choose an email pr"));
    }
    //abrir whatsapp con el contacto del tutor
    public void onClickWhatsApp(View view) {
            Uri uri = Uri.parse("smsto:" + telefono);
            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
            String text = "Hola "+ name + ", quiero ponerme en contacto contigo";
            i.putExtra(Intent.EXTRA_TEXT, text);
            i.setPackage("com.whatsapp");
            startActivity(Intent.createChooser(i, ""));
    }

    //insert a la tabla de "match" en la bd
    public void solicitar(){
        Thread tHilo;
        tHilo = new Thread() {
            @Override
            public void run() {
                super.run();
                StringRequest request = new StringRequest(Request.Method.POST, "https://senseii.000webhostapp.com/sendNoti.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.wtf("response", response);
                                if(response.contains("1")){
                                    Toast.makeText(getApplicationContext(),"su solicitud ha sido envianda", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
                    //mandamos los datos del registro
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("id_tut",id+"");
                        params.put("id_alu",id_alu+"");
                        Log.wtf("ids", id+" id tut " + id_alu  + " id alu" );
                        return params;
                    }
                };
                Volley.newRequestQueue(getApplicationContext()).add(request);
            }
        };
        tHilo.start();
    }
}