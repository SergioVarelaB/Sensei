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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilTutAlu extends AppCompatActivity {
    Intent in = getIntent();
    TextView tvName, tvTelefon, tvCorreo, tvComents, tvConocimientos;
    String telefono, correo, name, comentarios, conocimientos;
    ImageView ivPhono, ivMail, ivComents;
    int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_tut_alu);
        Intent intent = getIntent();
        id = intent.getIntExtra("id_tutor", 0);
        name = intent.getStringExtra("name");
        telefono = intent.getStringExtra("telefono");
        correo = intent.getStringExtra("correo");
        comentarios = intent.getStringExtra("comentarios");
        conocimientos = intent.getStringExtra("conocimienos");
        ivPhono = findViewById(R.id.ivMarcar);
        tvName = findViewById(R.id.tvNombreTut2);
        ivComents = findViewById(R.id.ivComents2);
        tvConocimientos = findViewById(R.id.tvConoc);
        tvConocimientos.setText(conocimientos);
        Log.wtf("conoss de jamonsisho", conocimientos);
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
        //int id = in.getIntExtra("id_tutor",-1);
        //Toast.makeText(this,""+id,Toast.LENGTH_SHORT).show();
    }
    public void dial(){
        Intent in = new Intent(Intent.ACTION_DIAL);
        in.setData(Uri.parse("tel:" + telefono));
        Toast.makeText(getApplicationContext(),"ss",Toast.LENGTH_SHORT).show();
        startActivity(in);
    }
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

    public void onClickWhatsApp(View view) {
            Uri uri = Uri.parse("smsto:" + telefono);
            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
            String text = "Hola "+ name + ", quiero ponerme en contacto contigo";
            i.putExtra(Intent.EXTRA_TEXT, text);
            i.setPackage("com.whatsapp");
            startActivity(Intent.createChooser(i, ""));
    }
}