package com.example.clase05persistenciadatossqlite.activities

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.clase05persistenciadatossqlite.R
import com.example.clase05persistenciadatossqlite.db.ManejadorBaseDatos
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class AgregarActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private  lateinit var fabAgregar: FloatingActionButton
    private  lateinit var etAlbum: EditText
    private  lateinit var etPrecio: EditText
    private  lateinit var spGenero: Spinner
    private val generos = arrayOf("Pop", "Rock", "Soul", "R&B", "Indie")
    private var generoSeleccionado: String = ""
    private  lateinit var tvAlbum: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)
        inicializarVistas()

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, generos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spGenero.adapter = adapter
        spGenero.onItemSelectedListener = this
        fabAgregar.setOnClickListener{
            insertarAlbum( etAlbum.text.toString(),  etPrecio.text.toString().toFloat(), generoSeleccionado)
        }
    }

    val columnaID = "id"
    val columnaNombreAlbum = "nombre"
    val columnaPrecio = "precio"
    val columnaGenero = "genero"
    var id: Int = 0
    private fun insertarAlbum(nombreAlbum: String, precio: Float, genero: String){
       if(!TextUtils.isEmpty(genero)) {
           val baseDatos = ManejadorBaseDatos(this)
           //  val columnas = arrayOf(columnaID, columnaNombreAlbum, columnaPrecio, columnaGenero)
           val contenido = ContentValues()
           contenido.put(columnaNombreAlbum, nombreAlbum)
           contenido.put(columnaPrecio, precio)
           contenido.put(columnaGenero, genero)
           //guardar imagen
           id = baseDatos.insertar(contenido).toInt()
           if (id > 0) {
               Toast.makeText(this, "Album " + nombreAlbum + " agregado", Toast.LENGTH_LONG).show()
               finish()
           } else
               Toast.makeText(this, "Ups no se pudo guardar el album", Toast.LENGTH_LONG).show()
           baseDatos.cerrarConexion()
       }else{
           Snackbar.make(tvAlbum,"Favor seleccionar un género", 0).show()
       }
    }

    private fun inicializarVistas(){
        etAlbum = findViewById(R.id.etAlbum)
        fabAgregar = findViewById(R.id.fabAgregar)
        etPrecio = findViewById(R.id.etPrecio)
        spGenero = findViewById(R.id.spGenero)
        tvAlbum = findViewById(R.id.tvAlbum)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
        generoSeleccionado = generos[position]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }



}