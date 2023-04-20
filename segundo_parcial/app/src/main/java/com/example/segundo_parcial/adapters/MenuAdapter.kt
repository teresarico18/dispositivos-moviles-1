package com.example.segundo_parcial.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.segundo_parcial.R
import com.example.segundo_parcial.models.Menu

class MenuAdapter(menus: ArrayList<Menu>, context: Context) :
RecyclerView.Adapter<MenuAdapter.ContenedorDeVista> (){
    var inner_menus: ArrayList<Menu> = menus
    var inner_context: Context = context

    inner class ContenedorDeVista(view: View) :
        RecyclerView.ViewHolder(view){
            val tvTexto : TextView
            val ivImagen : ImageView

            init{
                tvTexto = view.findViewById(R.id.tvTexto)
                ivImagen = view.findViewById(R.id.ivImagen)
            }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ContenedorDeVista {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_main2, viewGroup, false)
        return ContenedorDeVista(view)
    }

    override fun getItemCount(): Int {
        return inner_menus.size
    }

    override fun onBindViewHolder(holder: ContenedorDeVista, position: Int) {
        val menu: Menu = inner_menus.get(position)

        holder.tvTexto.text = menu.titulo
        holder.ivImagen.setImageResource(menu.imagen)
    }

}