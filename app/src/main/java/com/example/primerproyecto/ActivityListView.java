package com.example.primerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.primerproyecto.configuraciones.SQLiteConexion;
import com.example.primerproyecto.configuraciones.Transacciones;
import com.example.primerproyecto.tablas.Empleados;
import java.util.ArrayList;

public class ActivityListView extends AppCompatActivity {
    SQLiteConexion conexion;
    ListView lista;
    ArrayList<Empleados> listaempleados;
    ArrayList<String> ArregloEmpleados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        lista = (ListView) findViewById(R.id.lista);

        ObtenerListaEmpleados();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloEmpleados);

        lista.setAdapter(adp);

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void ObtenerListaEmpleados()
    {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Empleados list_emple = null;
        listaempleados = new ArrayList<Empleados>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Transacciones.tablaEmpleados, null);
        while(cursor.moveToNext())
        {
            list_emple = new Empleados();
            list_emple.setId(cursor.getInt(0));
            list_emple.setNombres(cursor.getString(1));
            list_emple.setApellidos(cursor.getString(2));
            list_emple.setEdad(cursor.getInt(3));
            list_emple.setCorreo(cursor.getString(4));

            listaempleados.add(list_emple);
        }
        cursor.close();
        llenarlista();
    }

    private void llenarlista()
    {
        ArregloEmpleados = new ArrayList<String>();
        for(int i=0; i<listaempleados.size(); i++)
        {
            ArregloEmpleados.add(listaempleados.get(i).getId()+" | "+
                    listaempleados.get(i).getNombres()+" | "+
                    listaempleados.get(i).getApellidos());
        }
    }
}