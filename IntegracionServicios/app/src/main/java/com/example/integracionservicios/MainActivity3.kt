package com.example.integracionservicios

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity3 : AppCompatActivity() {
    private lateinit var tvPrefNombre: TextView
    private lateinit var tvPrefEdad: TextView
    private lateinit var tvPrefAltura: TextView
    private lateinit var tvPrefDinero: TextView
    private lateinit var bnRegresar: Button
    private var nombre: String = ""
    private var edad: Int = 0
    private var altura: Float = 0F
    private var dinero: Float = 0F
    private var isChecked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        bnRegresar = findViewById(R.id.bnRegresar)
        bnRegresar.setOnClickListener{
            val i = Intent(this, ListaAnimales::class.java)
            startActivity(i)
        }
        val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
        isChecked = miSharedPreferences.getBoolean("switch_estado", true)
        if (isChecked){
            nombre = miSharedPreferences.getString("nombre", "").toString()
            edad = miSharedPreferences.getInt("edad", 0)
            altura = miSharedPreferences.getFloat("altura", 0F)
            dinero = miSharedPreferences.getFloat("dinero", 0F)
        } else {
            val i = intent
            nombre = i.getStringExtra("nombre").toString()
            edad = i.getIntExtra("edad", 0)
            altura = intent.getFloatExtra("altura", 0F)
            dinero = intent.getFloatExtra("dinero", 0F)
        }
        Log.d("Datos", isChecked.toString())
        Log.d("Datos", nombre)
        Log.d("Datos", edad.toString())
        Log.d("Datos", altura.toString())
        Log.d("Datos", dinero.toString())
        tvPrefNombre = findViewById(R.id.tvPrefNombre)
        tvPrefNombre.setText(nombre)
        tvPrefEdad = findViewById(R.id.tvPrefEdad)
        tvPrefEdad.setText(edad.toString())
        tvPrefAltura = findViewById(R.id.tvPrefAltura)
        tvPrefAltura.setText(altura.toString())
        tvPrefDinero = findViewById(R.id.tvPrefDinero)
        tvPrefDinero.setText(dinero.toString())
    }
}