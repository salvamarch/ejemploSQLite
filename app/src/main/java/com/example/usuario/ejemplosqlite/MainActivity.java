package com.example.usuario.ejemplosqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText nombre, apellidos, nota,id;
    Button insertar, listar, borrar,actualizar;
    ListView lista;
    MiBaseDeDatos baseDeDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.editTextNombre);
        apellidos = findViewById(R.id.editTextApellidos);
        nota = findViewById(R.id.editTextNota);
        insertar = findViewById(R.id.button);
        baseDeDatos = new MiBaseDeDatos(this);
        listar = findViewById(R.id.buttonListar);
        lista = findViewById(R.id.lista);
        borrar = findViewById(R.id.buttonBorrar);
        id = findViewById(R.id.editTextID);
        actualizar = findViewById(R.id.buttonActualizar);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean resultado = false;

                resultado = baseDeDatos.insertar(nombre.getText().toString(),
                        apellidos.getText().toString(),
                        nota.getText().toString());

                if (resultado) {
                    Toast.makeText(MainActivity.this, "Insertado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error en la insercción.", Toast.LENGTH_SHORT).show();

                }

            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = baseDeDatos.listar();
                ArrayAdapter <String> adapter;
                List<String> list = new ArrayList<String>();



                if ((cursor!=null) && (cursor.getCount()>0)){
                    while(cursor.moveToNext()){
                        String fila = "";
                        fila+= "ID: "+cursor.getString(0);
                        fila+= " NOMBRE: "+cursor.getString(1);
                        fila+= " APELLIDOS: "+cursor.getString(2);
                        fila+= " NOTA: "+cursor.getString(3);
                        list.add(fila);
                    }
                    adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,list);
                    lista.setAdapter(adapter);
                    cursor.close();

                }else{
                    Toast.makeText(MainActivity.this, "Base de datos vacía.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean resultado = baseDeDatos.borrar(id.getText().toString());
                if (resultado){
                    Toast.makeText(MainActivity.this, "Se ha borrado correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Nada que borrar", Toast.LENGTH_SHORT).show();
                }

            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean resultado = baseDeDatos.actualizar(id.getText().toString(),
                        nombre.getText().toString(),apellidos.getText().toString(),nota.getText().toString());

                if (resultado){
                    Toast.makeText(MainActivity.this, "Se ha actualizado correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Nada que actualizar", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
