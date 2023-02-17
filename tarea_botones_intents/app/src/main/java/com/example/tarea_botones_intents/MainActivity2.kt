package com.example.tarea_botones_intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    private lateinit var tvReprobado: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var calificacion = intent.getStringExtra("calificacion")
        var texto = "Obtuviste un " + calificacion
        tvReprobado = findViewById(R.id.tvReprobado)
        tvReprobado.setText(texto)
        Log.d("Datos", "Datos recibidos con éxito fue $calificacion")

        var bnRegresar = findViewById<Button>(R.id.bnRegresar)
        bnRegresar.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            //finish()
        }
    }
}