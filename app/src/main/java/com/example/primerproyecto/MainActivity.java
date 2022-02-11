package com.example.primerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    Button btnAgregar, btnPasar;
    EditText txtNombre;
    EditText txtApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        btnAgregar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(),"Bienvenido "+txtNombre.getText().toString()+" "+txtApellido.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
        btnPasar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });

    }
}