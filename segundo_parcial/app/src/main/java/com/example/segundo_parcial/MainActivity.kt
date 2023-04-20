package com.example.segundo_parcial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.segundo_parcial.adapters.MenuAdapter
import com.example.segundo_parcial.models.FakeMenus

class MainActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menus = FakeMenus().getMenus()
        recycler = findViewById<RecyclerView>(R.id.recyclerMenus)


        val administradorDeLayouts = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        recycler.layoutManager = administradorDeLayouts
        recycler.adapter = MenuAdapter(menus, this)
    }
}