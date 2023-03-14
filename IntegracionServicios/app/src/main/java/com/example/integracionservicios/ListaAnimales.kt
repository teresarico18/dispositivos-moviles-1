package com.example.integracionservicios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.integracionservicios.adapters.AnimalesAdapter
import com.example.integracionservicios.models.FakeAnimales

class ListaAnimales : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_animales)

        val animales = FakeAnimales().getAnimales()
        val recycler = findViewById<RecyclerView>(R.id.recyclerAnimales)

        val CANTIDAD_COLUMNAS = 2
        val administradorDeLayouts = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = administradorDeLayouts
        recycler.adapter = AnimalesAdapter(animales, this)

    }
}