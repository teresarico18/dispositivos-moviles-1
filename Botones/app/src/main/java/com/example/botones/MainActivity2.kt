package com.example.botones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var fraseRecibida = intent.getStringExtra("frase")
        var edad = intent.getIntExtra("edad", 0)

        Log.d("Datos", "Datos recibidos con éxito fueron frase $fraseRecibida y $edad")

        var btnAbrirPantalla = findViewById<Button>(R.id.btnPantalla)
        btnAbrirPantalla.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        var btnCerrar = findViewById<Button>(R.id.btnCerrar)
        btnCerrar.setOnClickListener{finish();}
    }
}