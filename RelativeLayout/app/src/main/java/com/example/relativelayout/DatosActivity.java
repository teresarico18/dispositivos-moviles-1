package com.example.relativelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DatosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        TextView tvValor1 = findViewById(R.id.tvValor1);
        TextView tvValor2 = findViewById(R.id.tvValor2);
        Button bnRegresar = findViewById(R.id.bnRegresar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String canal = extras.getString("Canal");
            String programa = extras.getString("Programa");
            tvValor1.setText(canal);
            tvValor2.setText(programa);
        }

        bnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DatosActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}