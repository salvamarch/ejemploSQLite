package com.example.usuario.ejemplosqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText nombre, apellidos,nota;
Button insertar;
MiBaseDeDatos baseDeDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.editTextNombre);
        apellidos = findViewById(R.id.editTextApellidos);
        nota = findViewById(R.id.editTextNota);
        insertar = findViewById(R.id.button);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean resultado = false;

                resultado = baseDeDatos.insertar(nombre.getText().toString(),
                        apellidos.getText().toString(),
                        nota.getText().toString());

                if (resultado){
                    Toast.makeText(MainActivity.this, "Insertado correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error en la insercción.", Toast.LENGTH_SHORT).show();

                }

            }
        });



    }
}
