package com.example.jurara.archivos2;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {


    EditText edi;
    Button btnG,btnA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edi=(EditText)findViewById(R.id.txtinput);
        btnG=(Button)findViewById(R.id.button);
        btnA=(Button)findViewById(R.id.button2);




        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatos();
            }
        });

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarDatos();
            }
        });
    }

    public void guardarDatos() {

        FileOutputStream flujo=null;
        OutputStreamWriter escritor = null;
        try
        {
            File ruta = Environment.getExternalStorageDirectory();
            File fichero = new File(ruta.getAbsolutePath(), "ficheroPrueba.txt");
            flujo =new FileOutputStream(fichero);
            escritor=new OutputStreamWriter(flujo);
            escritor.write(edi.getText().toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        finally
        {
            try {
                if(escritor!=null)
                    escritor.close();
                Toast.makeText(getBaseContext(), "¡Archivo guardado correctamente!", Toast.LENGTH_SHORT).show();
                edi.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void cargarDatos() {
        InputStreamReader flujo=null;
        BufferedReader lector=null;
        try
        {
            File ruta = Environment.getExternalStorageDirectory();
            File fichero = new File(ruta.getAbsolutePath(), "ficheroPrueba.txt");
            flujo= new InputStreamReader(new FileInputStream(fichero));
            lector= new BufferedReader(flujo);
            String texto = lector.readLine();
            while(texto!=null)
            {
                edi.setText(texto);
                texto=lector.readLine();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try {
                if(lector!=null)
                    lector.close();
                Toast.makeText(getBaseContext(), "¡Archivo cargado correctamente!",
                        Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

