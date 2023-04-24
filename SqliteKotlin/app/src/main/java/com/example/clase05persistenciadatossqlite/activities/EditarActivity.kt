package com.example.clase05persistenciadatossqlite.activities

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.clase05persistenciadatossqlite.R
import com.example.clase05persistenciadatossqlite.db.ManejadorBaseDatos
import com.example.clase05persistenciadatossqlite.modelos.Album
import com.google.android.material.snackbar.Snackbar

class EditarActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var bnGuardar: Button
    private lateinit var etAlbum: EditText
    private lateinit var etPrecio: EditText
    private lateinit var spGenero: Spinner
    private val generos = arrayOf("Pop", "Rock", "Soul", "R&B", "Indie")
    private var generoSeleccionado: String = ""
    private lateinit var tvAlbum: TextView
    var album: Album? = null
    var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)
        //  setSupportActionBar(toolbar)
        getSupportActionBar()?.title = "Edición"
        getSupportActionBar()?.setHomeButtonEnabled(true);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        inicializarVistas()
        id = intent.getIntExtra("id", 0)
        buscarAlbum(id)
        poblarCampos()
    }

    private fun poblarCampos() {
        etAlbum.setText(album?.nombre)
        etPrecio.setText(album?.precio.toString())
        val position = generos.indexOf(album?.genero)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, generos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spGenero.adapter = adapter
        spGenero.onItemSelectedListener = this
        if (position >= 0) {
            spGenero.setSelection(position)
            generoSeleccionado = generos[position]
        }
    }

    private fun inicializarVistas() {
        etAlbum = findViewById(R.id.etAlbum)
        bnGuardar = findViewById(R.id.bnGuardar)
        etPrecio = findViewById(R.id.etPrecio)
        spGenero = findViewById(R.id.spGenero)
        tvAlbum = findViewById(R.id.tvAlbum)
        bnGuardar.setOnClickListener {
            var precio_actual: Float
            precio_actual = etPrecio.text.toString().toFloat()
            actualizarAlbum(etAlbum.text.toString(), precio_actual, generoSeleccionado)
        }
    }

    val columnaNombreAlbum = "nombre"
    val columnaPrecio = "precio"
    val columnaGenero = "genero"

    private fun actualizarAlbum(nombreAlbum: String, precio: Float, genero: String) {
        if (!TextUtils.isEmpty(genero)) {
            val baseDatos = ManejadorBaseDatos(this)
            val contenido = ContentValues()
            contenido.put(columnaNombreAlbum, nombreAlbum)
            contenido.put(columnaPrecio, precio)
            contenido.put(columnaGenero, genero)
            if ( id > 0) {
                val argumentosWhere = arrayOf(id.toString())
                val id_actualizado = baseDatos.actualizar(contenido, "id = ?", argumentosWhere)
                if (id_actualizado > 0) {
                    Snackbar.make(etAlbum, "Album actualizado", Snackbar.LENGTH_LONG).show()
                } else {
                    val alerta = AlertDialog.Builder(this)
                    alerta.setTitle("Atención")
                        .setMessage("No fue posible actualizarlo")
                        .setCancelable(false)
                        .setPositiveButton("Aceptar") { dialog, which ->

                        }
                        .show()
                }
            } else {
                Toast.makeText(this, "no hiciste id", Toast.LENGTH_LONG).show()
            }
            baseDatos.cerrarConexion()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("Range")
    private fun buscarAlbum(idAlbum: Int) {

        if (idAlbum > 0) {
            val baseDatos = ManejadorBaseDatos(this)
            val columnasATraer = arrayOf("id", "nombre", "precio", "genero")
            val condicion = " id = ?"
            val argumentos = arrayOf(idAlbum.toString())
            val ordenarPor = "id"
            val cursor = baseDatos.seleccionar(columnasATraer, condicion, argumentos, ordenarPor)

            if (cursor.moveToFirst()) {
                do {
                    val album_id = cursor.getInt(cursor.getColumnIndex("id"))
                    val nombre = cursor.getString(cursor.getColumnIndex("nombre"))
                    val precio = cursor.getFloat(cursor.getColumnIndex("precio"))
                    val genero = cursor.getString(cursor.getColumnIndex("genero"))
                    album = Album(album_id, nombre, precio, genero)
                } while (cursor.moveToNext())
            }
            baseDatos.cerrarConexion()
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        generoSeleccionado = generos[position]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}