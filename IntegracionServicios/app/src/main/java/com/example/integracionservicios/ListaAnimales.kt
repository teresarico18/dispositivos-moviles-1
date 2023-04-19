package com.example.integracionservicios

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.integracionservicios.adapters.AnimalesAdapter
import com.example.integracionservicios.models.FakeAnimales

class ListaAnimales : AppCompatActivity() {
    private lateinit var bnCol1: Button
    private lateinit var bnCol2: Button
    private lateinit var bnCol3: Button
    private lateinit var bnVertical: Button
    private lateinit var bnHorizontal: Button
    private var nombre: String = ""
    private var edad: Int = 0
    private var altura: Float = 0F
    private var dinero: Float = 0F
    private var isChecked: Boolean = false
    private var CANTIDAD_COLUMNAS = 1
    private var ORIENTACION = GridLayoutManager.VERTICAL
    private lateinit var administradorDeLayouts: GridLayoutManager
    private lateinit var recycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_animales)

        val animales = FakeAnimales().getAnimales()
        //val recycler = findViewById<RecyclerView>(R.id.recyclerAnimales)
        //DECLARED BEFORE
        recycler = findViewById<RecyclerView>(R.id.recyclerAnimales)

        //BASIC LINEAR LAYOUT FOR RECYCLER VIEW
        //val administradorDeLayouts = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        administradorDeLayouts = GridLayoutManager(this, CANTIDAD_COLUMNAS, ORIENTACION, false)
        recycler.layoutManager = administradorDeLayouts
        recycler.adapter = AnimalesAdapter(animales, this)

        bnCol1 = findViewById(R.id.bnCol1)
        bnCol1.setOnClickListener{
            CANTIDAD_COLUMNAS = 1
            actualizarLayout()
        }
        bnCol2 = findViewById(R.id.bnCol2)
        bnCol2.setOnClickListener{
            CANTIDAD_COLUMNAS = 2
            actualizarLayout()
        }
        bnCol3 = findViewById(R.id.bnCol3)
        bnCol3.setOnClickListener{
            CANTIDAD_COLUMNAS = 3
            actualizarLayout()
        }
        bnVertical = findViewById(R.id.bnVertical)
        bnVertical.setOnClickListener{
            ORIENTACION = GridLayoutManager.VERTICAL
            actualizarLayout()
        }
        bnHorizontal = findViewById(R.id.bnHorizontal)
        bnHorizontal.setOnClickListener{
            ORIENTACION = GridLayoutManager.HORIZONTAL
            actualizarLayout()
        }

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val i = Intent(this, MainActivity3::class.java)
            if (!isChecked){
                i.putExtra("nombre", nombre)
                i.putExtra("edad", edad)
                i.putExtra("altura", altura)
                i.putExtra("dinero", dinero)
            }
            startActivity(i)
        }
    }
    private fun actualizarLayout() {
        administradorDeLayouts = GridLayoutManager(this, CANTIDAD_COLUMNAS, ORIENTACION, false)
        recycler.layoutManager = administradorDeLayouts
    }
}
/* val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
        isChecked = miSharedPreferences.getBoolean("switch_estado", true)
        if (!isChecked){
            val i = intent
            nombre = i.getStringExtra("nombre").toString()
            edad = i.getIntExtra("edad", 0)
            altura = intent.getFloatExtra("altura", 0F)
            dinero = intent.getFloatExtra("dinero", 0F)
            Log.d("Datos", isChecked.toString())
            Log.d("Datos", nombre)
            Log.d("Datos", edad.toString())
            Log.d("Datos", altura.toString())
            Log.d("Datos", dinero.toString())
}*/