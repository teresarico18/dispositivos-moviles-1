package com.example.botones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var tvPhrase: TextView
    private lateinit var etPhrase: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPhrase = findViewById(R.id.textViewPhrase)
        etPhrase = findViewById(R.id.editTextPhrase)

        var btnMostrar = findViewById<Button>(R.id.buttonShow)
        btnMostrar.setOnClickListener{
            val Phrase = etPhrase.text.toString()
            Snackbar.make(tvPhrase, "Su frase fue $Phrase", Snackbar.LENGTH_LONG).show()
        }

        var btnSiguiente = findViewById<Button>(R.id.btnSiguiente)
        btnSiguiente.setOnClickListener{
            val i = Intent(this, MainActivity2::class.java) //cambiar vista
            i.putExtra("frase", etPhrase.text.toString()) // mandar datos
            i.putExtra("edad", 21)
            startActivity(i)
        }
    }
    private fun obtenerFrase(): String{
        return etPhrase.text.toString()
    }
}