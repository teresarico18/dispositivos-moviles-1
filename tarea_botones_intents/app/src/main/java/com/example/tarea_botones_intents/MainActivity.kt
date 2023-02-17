package com.example.tarea_botones_intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var etCalificacion: EditText
    private var calificacion: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCalificacion = findViewById(R.id.etCalificacion)

        var bnEnviar = findViewById<Button>(R.id.bnEnviar)
        bnEnviar.setOnClickListener{
            calificacion = etCalificacion.text.toString()
            try {
                if (Integer.valueOf(calificacion) in 7..10){
                    val i = Intent(this, MainActivity4::class.java) //cambiar vista
                    i.putExtra("calificacion", calificacion)
                    startActivity(i)
                    Snackbar.make(etCalificacion, "Aprobaste: $calificacion", Snackbar.LENGTH_LONG).show()
                } else if (Integer.valueOf(calificacion)  < 7) {
                    val i = Intent(this, MainActivity2::class.java) //cambiar vista
                    i.putExtra("calificacion", calificacion)
                    startActivity(i)
                } else {
                    Toast.makeText(this, "Calificacion invalida.", Toast.LENGTH_LONG).show()
                }
            } catch (e: java.lang.NumberFormatException){
                Toast.makeText(this, "Calificacion invalida.", Toast.LENGTH_LONG).show()
            }
        }
    }
}