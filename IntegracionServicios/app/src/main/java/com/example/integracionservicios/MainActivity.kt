package com.example.integracionservicios

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import androidx.appcompat.app.ActionBar

class MainActivity : AppCompatActivity() {
    private lateinit var tvBienvenido: TextView
    private lateinit var etNombre: EditText
    private lateinit var etEdad: EditText
    private lateinit var etAltura: EditText
    private lateinit var etDinero: EditText
    private lateinit var bnGuardar: Button
    private lateinit var switchPreferencias: Switch
    private val NOMBRE_KEY = "nombre"
    private val EDAD_KEY = 0
    private val ALTURA_KEY = 0F
    private val DINERO_KEY = 0F
    private val SWITCH_KEY = "switch_estado"
    private val NOMBRE_INSTANCIA = "nombre_instancia"
    private var nombre: String = ""
    private var edad: Int = 0
    private var altura: Float = 0F
    private var dinero: Float = 0F
    private var isChecked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("PREFERENCIAS", "onCreate")
        setContentView(R.layout.activity_main)

        var actionBar: ActionBar?
        actionBar = supportActionBar
        var colorDrawable: ColorDrawable
        colorDrawable = ColorDrawable(Color.parseColor("#FF018786"))
        actionBar!!.setBackgroundDrawable(colorDrawable)

        inicializarVistas()

        Log.d("PREFERENCIAS", savedInstanceState?.getString(NOMBRE_KEY).toString())

        val switchPreferencias = findViewById<Switch>(R.id.switchPreferencias)
        switchPreferencias.setOnCheckedChangeListener { compoundButton: CompoundButton, b: Boolean ->
            isChecked = !isChecked
            Log.d("Datos", "Datos recibidos con éxito fue $isChecked")
        }

//nombre = savedInstanceState?.getString(NOMBRE_KEY).toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("PREFERENCIAS", "onSaveInstanceState")
        outState.putString(NOMBRE_KEY, nombre )
        outState?.run {
            putString(NOMBRE_KEY, nombre)
        }
// call superclass to save any view hierarchy
        super.onSaveInstanceState(outState)

    }

    override fun onResume() {
        Log.d("PREFERENCIAS", "onResume")
        if(TextUtils.isEmpty(nombre)){
            val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
            nombre = miSharedPreferences.getString(NOMBRE_KEY, "").toString()

        }

        tvBienvenido.text = nombre
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

    private fun cambiarTextoBienvenida(nombre: String) {
        if (!TextUtils.isEmpty(nombre)) {
            tvBienvenido.text = "Bienvenido " + nombre
        }
    }

    private fun inicializarVistas() {
        tvBienvenido = findViewById(R.id.tvBienvenido)
        etNombre = findViewById(R.id.etNombre)
        etEdad = findViewById(R.id.etEdad)
        etAltura = findViewById(R.id.etAltura)
        etDinero = findViewById(R.id.etDinero)
        bnGuardar = findViewById(R.id.bnGuardar)
        switchPreferencias = findViewById(R.id.switchPreferencias)


        bnGuardar.setOnClickListener {
            try {
                nombre = etNombre.text.toString()
                edad = etEdad.text.toString().toInt()
                altura = etAltura.text.toString().toFloat()
                dinero = etDinero.text.toString().toFloat()
                cambiarTextoBienvenida(nombre)
                val i = Intent(this, ListaAnimales::class.java)
                if (isChecked){
                    val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
                    val editor = miSharedPreferences.edit()
                    editor.putString(SWITCH_KEY, isChecked.toString())
                    editor.putString(NOMBRE_KEY, nombre)
                    editor.putString(EDAD_KEY.toString(), edad.toString())
                    editor.putString(ALTURA_KEY.toString(), altura.toString())
                    editor.putString(DINERO_KEY.toString(), dinero.toString())
                    editor.putString(NOMBRE_KEY, nombre)
                    editor.apply()
                }else{
                    val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
                    val editor = miSharedPreferences.edit()
                    editor.putString(SWITCH_KEY, isChecked.toString())
                    editor.apply()
                    i.putExtra("nombre", nombre)
                    i.putExtra("edad", edad)
                    i.putExtra("altura", altura)
                    i.putExtra("dinero", dinero)
                }
                startActivity(i)
            } catch (e: java.lang.NumberFormatException) {
                Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show()
            }

        }

    }
}