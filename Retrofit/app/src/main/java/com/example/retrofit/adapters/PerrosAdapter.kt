package com.example.retrofit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.models.Perro
import com.squareup.picasso.Picasso

class PerrosAdapter(perros: List<String>?, context: Context) : RecyclerView.Adapter<PerrosAdapter.ContenedorDeVista>(){
    var inner_perros: List<String>? = perros
    var inner_context: Context = context

    inner class ContenedorDeVista(view:View) :
            RecyclerView.ViewHolder(view), View.OnClickListener {
                val ivPerro : ImageView
                val tvLinkPerro : TextView
                init {
                    ivPerro = view.findViewById(R.id.ivPerro)
                    tvLinkPerro = view.findViewById(R.id.tvLinkPerro)
                }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContenedorDeVista {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main2, parent, false)
        return ContenedorDeVista(view)
    }

    override fun onBindViewHolder(holder: ContenedorDeVista, position: Int) {
        val perro = inner_perros?.get(position)
        holder.tvLinkPerro.text = perro
        //val perro: List<String> = inner_perros.get(position)
        Picasso.get().load(perro).into(holder.ivPerro);
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}