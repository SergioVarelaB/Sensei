package com.example.sensei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,pass;
    TextView registro;
    Button btn;
    String nombre = "temo";
    String passwd = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.etName);
        pass = findViewById(R.id.etPass);
        registro = findViewById(R.id.tvRegs);
        btn = findViewById(R.id.btnOk);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String p = pass.getText().toString();
                if(n==nombre && p == passwd){
                    Toast.makeText(getApplicationContext(),"aqui deberia aparecer algo",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(),"nel prro",Toast.LENGTH_LONG).show();
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Registro.class));
            }
        });


    }
}
