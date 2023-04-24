package com.example.clase05persistenciadatossqlite.activities

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clase05persistenciadatossqlite.R
import com.example.clase05persistenciadatossqlite.adapters.AlbumesAdapter
import com.example.clase05persistenciadatossqlite.db.ManejadorBaseDatos
import com.example.clase05persistenciadatossqlite.interfaces.albumesInterface
import com.example.clase05persistenciadatossqlite.modelos.Album
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListadoActivity : AppCompatActivity(), albumesInterface {

    //private lateinit var listView: ListView
    private lateinit var recycler: RecyclerView
    private var listaDeAlbumes = ArrayList<Album>()
    private lateinit var fab: FloatingActionButton
    private val ORDENAR_POR_NOMBRE : String  = "nombre"
    val columnas = arrayOf("id", "nombre","precio", "genero" )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)
        inicializarVistas()
        asignarEventos()
    }
    override fun onResume() {
        super.onResume()
        traerMisAlbumes()
    }
    private fun inicializarVistas(){
        //listView = findViewById(R.id.listView)
        recycler = findViewById(R.id.recyclerAlbumes)
        fab = findViewById(R.id.fab)
    }

    private fun asignarEventos(){
        fab.setOnClickListener{
            val intent = Intent(this, AgregarActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_listado, menu)
        val searchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val manejador = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(manejador.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                buscarAlbum("%" + p0 + "%")
                Toast.makeText(applicationContext, p0, Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if(TextUtils.isEmpty(p0)){
                    this.onQueryTextSubmit("");
                }
                return false
            }


        })
        return super.onCreateOptionsMenu(menu)
    }


    private fun traerMisAlbumes() {
        val baseDatos = ManejadorBaseDatos(this)
        val cursor = baseDatos.traerTodos(columnas, ORDENAR_POR_NOMBRE)
        recorrerResultados(cursor)
        baseDatos.cerrarConexion()
    }

    @SuppressLint("Range")
    private fun buscarAlbum(nombre: String) {
        val baseDatos = ManejadorBaseDatos(this)
        val camposATraer = arrayOf(nombre)
        val cursor = baseDatos.seleccionar(columnas,"nombre like ?", camposATraer, ORDENAR_POR_NOMBRE)
        recorrerResultados(cursor)
        baseDatos.cerrarConexion()
    }

    @SuppressLint("Range")
    fun recorrerResultados(cursor : Cursor){
        if(listaDeAlbumes.size > 0)
            listaDeAlbumes.clear()

        if(cursor.moveToFirst()){
            do{
                val album_id = cursor.getInt(cursor.getColumnIndex("id"))
                val nombre = cursor.getString(cursor.getColumnIndex("nombre"))
                val precio = cursor.getFloat(cursor.getColumnIndex("precio"))
                val genero = cursor.getString(cursor.getColumnIndex("genero"))
                val album: Album
                album = Album(album_id, nombre, precio, genero)
                listaDeAlbumes.add(album)
            }while(cursor.moveToNext())
        }
        val administradorLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false
        )

        recycler.layoutManager = administradorLayout
        recycler.adapter = AlbumesAdapter( listaDeAlbumes, this, this)
    }

    override fun albumEliminado() {
        Log.d("PRUEBAS", "albumEliminado")
        traerMisAlbumes()
    }

    override fun editarAlbum(album: Album) {
        Log.d("PRUEBAS", "editar Album "+album.id)
        val intent = Intent(this, EditarActivity::class.java)
        intent.putExtra("id",album.id)
        intent.putExtra("nombre",album.nombre)
        intent.putExtra("genero",album.genero)
        startActivity(intent)
    }

}