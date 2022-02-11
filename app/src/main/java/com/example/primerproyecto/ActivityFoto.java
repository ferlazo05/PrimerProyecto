package com.example.primerproyecto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityFoto extends AppCompatActivity {
    ImageView ObjImagen;
    Button btn;
    String CurrentPhotoPath;
    static final int PETICION_ACCESO_CAM = 100;
    static final int TAKE_PIC_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        ObjImagen = (ImageView) findViewById(R.id.Fotografia);
        btn = (Button) findViewById(R.id.btnimagen);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisos();
            }
        });
    }

    private void permisos()
    {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PETICION_ACCESO_CAM);
        }
        else
        {
            tomarfoto();
        }
    }

     public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
     {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
         if(requestCode == PETICION_ACCESO_CAM)
         {
             if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
             {
                 tomarfoto();
             }
         }
         else
         {
             Toast.makeText(getApplicationContext(), "Se necesitan permisos de accesso a la camara", Toast.LENGTH_SHORT).show();
         }
     }

    private void tomarfoto()
    {
        Intent takepic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takepic.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(takepic, TAKE_PIC_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Byte[] arreglo;

        if(requestCode == TAKE_PIC_REQUEST && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imagen = (Bitmap) extras.get("data");
            ObjImagen.setImageBitmap(imagen);
        }
    }
}