package com.example.segundo_parcial.models

import com.example.segundo_parcial.R

class FakeMenus {
    fun getMenus (): ArrayList<Menu>{
        val menus : ArrayList<Menu>
        menus = arrayListOf<Menu>()

        menus.add(Menu("Gatos", R.drawable.cat))
        menus.add(Menu("Perfil",  R.drawable.profile))
        menus.add(Menu("Configurar", R.drawable.config))
        menus.add(Menu("Cerrar", R.drawable.close))

        return menus
    }
}