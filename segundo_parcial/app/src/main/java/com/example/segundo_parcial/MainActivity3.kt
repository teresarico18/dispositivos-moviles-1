package com.example.segundo_parcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {
    private lateinit var tvBienvenido: TextView
    private lateinit var etDueno: EditText
    private lateinit var etGato: EditText
    private lateinit var etEdad: EditText
    private val DUENO_KEY = "dueno"
    private val GATO_KEY = "gato"
    private val EDAD_KEY = "edad"
    private var dueno: String = ""
    private var gato: String = ""
    private var edad: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("PREFERENCIAS", "onCreate")
        setContentView(R.layout.activity_main3)

        tvBienvenido = findViewById(R.id.tvBienvenido)
        etDueno = findViewById(R.id.etDueno)
        etGato = findViewById(R.id.etGato)
        etEdad = findViewById(R.id.etEdad)
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            try {
                dueno = etDueno.text.toString()
                gato = etGato.text.toString()
                if (dueno == "Nombre del dueño" || gato == "Nombre del gato"){
                    Toast.makeText(this, "Datos incompletos", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                edad = etEdad.text.toString().toInt()
                val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
                val editor = miSharedPreferences.edit()
                editor.putString(DUENO_KEY, dueno)
                editor.putString(GATO_KEY, gato)
                editor.putInt(EDAD_KEY, edad)
                editor.apply()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show()
            }catch (e: java.lang.NumberFormatException) {
                Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show()
            }

        }
    }
    override fun onResume() {
        Log.d("PREFERENCIAS", "onResume")
        if(TextUtils.isEmpty(dueno)){
            val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
            dueno = miSharedPreferences.getString(DUENO_KEY, "").toString()
            gato = miSharedPreferences.getString(GATO_KEY, "").toString()
            edad = miSharedPreferences.getInt(EDAD_KEY, 0)
        }
        if (dueno != ""){
            tvBienvenido.text = "Con datos previamente guardados"
            etDueno.setText(dueno)
            etGato.setText(gato)
            etEdad.setText(edad.toString())
        }else {
            etDueno.setText("Nombre del dueño")
            etGato.setText("Nombre del gato")
            etEdad.setText("Edad del gato")
        }
        super.onResume()
    }

    override fun onStart() {
        Log.d("PREFERENCIAS", "onStart")
        super.onStart()
    }

    override fun onPause() {
        Log.d("PREFERENCIAS", "onPause")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("PREFERENCIAS", "onDestroy")
        super.onDestroy()
    }

    /*private fun cambiarTextoBienvenida(nombre: String) {
        if (!TextUtils.isEmpty(nombre)) {
            tvBienvenido.text = "Bienvenido " + nombre
        }
    }*/
}