package com.example.relativelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ImageView ivContinuara;
    private String programa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bnCanal = findViewById(R.id.bnCambiar);
        EditText etCambiar = findViewById(R.id.etCambiar);
        TextView tvDespedida = findViewById(R.id.tvDespedida);
        Spinner spProgramas = findViewById(R.id.spProgramas);
        ivContinuara = findViewById(R.id.ivContinuara);
        spProgramas.setOnItemSelectedListener(this);

        bnCanal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //aqui la logaica de hacer click
                //obtendremos el valor del edittext y lo asignaremos al textview inferior
                String texto = etCambiar.getText().toString();
                Toast.makeText(MainActivity.this, "El valor era " + texto, Toast.LENGTH_LONG).show();
                tvDespedida.setText(texto);
            }
        });
        ivContinuara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DatosActivity.class);
                i.putExtra("Canal", tvDespedida.getText().toString());
                i.putExtra("Programa", programa);
                startActivity(i);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long fila) {
        programa = adapterView.getItemAtPosition(posicion).toString();
        Snackbar.make(adapterView, "Programa seleccionado "+programa, Snackbar.LENGTH_LONG).show();

        if (posicion == 0){
            ivContinuara.setImageResource(R.drawable.pic1);
        } else if (posicion == 1){
            ivContinuara.setImageResource(R.drawable.pic2);
        } else if (posicion == 2){
            ivContinuara.setImageResource(R.drawable.pic3);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}