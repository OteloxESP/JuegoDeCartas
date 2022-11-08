package com.example.juegodecartas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InicioActivity extends AppCompatActivity {

    EditText txtUsuario;
    Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        txtUsuario = findViewById(R.id.editTextUsuario);
        btnIniciar = findViewById(R.id.btnJugar);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtUsuario.getText().toString().equals("")){
                    Toast.makeText(InicioActivity.this,
                            "No has introducido ningun usuario",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent (view.getContext(), MainActivity.class);
                    intent.putExtra("usuario", txtUsuario.getText().toString());
                    startActivityForResult(intent, 0);
                }
            }
        });
    }
}