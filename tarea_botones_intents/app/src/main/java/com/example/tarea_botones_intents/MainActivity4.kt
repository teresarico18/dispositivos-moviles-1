package com.example.tarea_botones_intents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity4 : AppCompatActivity() {
    private lateinit var tvAprobado: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        var calificacion = intent.getStringExtra("calificacion")
        var texto = "Felicidades aprobaste la materia con " + calificacion
        tvAprobado = findViewById(R.id.tvAprobado)
        tvAprobado.setText(texto)

        Log.d("Datos", "Datos recibidos con éxito fue $calificacion")
    }
}