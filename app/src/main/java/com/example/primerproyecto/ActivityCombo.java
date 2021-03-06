package com.example.primerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.primerproyecto.configuraciones.SQLiteConexion;
import com.example.primerproyecto.configuraciones.Transacciones;
import com.example.primerproyecto.tablas.Empleados;

import java.util.ArrayList;

public class ActivityCombo extends AppCompatActivity {

    SQLiteConexion conexion;
    Spinner spempleados;
    EditText actxtnombres, actxtapellidos, acedad, accorreo;
    ArrayList<String> lista_empleados;
    ArrayList<Empleados> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        conexion = new SQLiteConexion(null, Transacciones.NameDatabase, null, 1);
        spempleados = (Spinner) findViewById(R.id.spempleados);
        actxtnombres = (EditText) findViewById(R.id.actxtnombres);
        actxtapellidos = (EditText) findViewById(R.id.actxtapellidos);
        acedad = (EditText) findViewById(R.id.acedad);
        accorreo = (EditText) findViewById(R.id.accorreo);

        ObtenerListaEmpleados();

        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista_empleados);
        spempleados.setAdapter(adp);

        spempleados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                actxtnombres.setText(lista.get(i).getNombres());
                actxtapellidos.setText(lista.get(i).getApellidos());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void ObtenerListaEmpleados()
    {
        Empleados emple = null;
        lista = new ArrayList<Empleados>();
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Transacciones.tablaEmpleados, null);

        while(cursor.moveToNext())
        {
            emple = new Empleados();
            emple.setId(cursor.getInt(0));
            emple.setNombres(cursor.getString(1));
            emple.setApellidos(cursor.getString(2));
            emple.setEdad(cursor.getInt(3));
            emple.setCorreo(cursor.getString(4));

            lista.add(emple);
        }
        cursor.close();

        fillCombo();
    }

    private void fillCombo()
    {
        lista_empleados = new ArrayList<String>();

        for(int i=0; i<lista.size(); i++)
        {
            lista_empleados.add(lista.get(i).getId()+" | "+
                    lista.get(i).getNombres()+" | "+
                    lista.get(i).getApellidos());
        }
    }
}