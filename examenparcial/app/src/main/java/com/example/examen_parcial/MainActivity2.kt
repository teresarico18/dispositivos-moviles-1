package com.example.examen_parcial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    private lateinit var tvMensaje: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val horario = intent.getStringExtra("horario")
        val pelicula = intent.getStringExtra("pelicula")
        tvMensaje = findViewById(R.id.tvMensaje)
        val mensaje = "Tu pelicula es $pelicula en horario $horario"
        tvMensaje.setText(mensaje)
    }
}