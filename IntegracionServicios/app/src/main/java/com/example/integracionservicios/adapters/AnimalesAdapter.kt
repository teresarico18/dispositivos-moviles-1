package com.example.integracionservicios.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.integracionservicios.R
import com.example.integracionservicios.models.Animal

class AnimalesAdapter(videojuegos: ArrayList<Animal>, context: Context) :
    RecyclerView.Adapter<AnimalesAdapter.ContenedorDeVista> (){
    var inner_animales: ArrayList<Animal> = videojuegos
    var inner_context: Context = context

    inner class ContenedorDeVista(view: View) : // : siginifca heredar propiedades del viewHolder
        RecyclerView.ViewHolder(view){
            //aqui haremos el inflate del layout
            val tvNombre : TextView
            val tvPrecio : TextView
            val ivImagen : ImageView
            val tvProducto : TextView
            val bnAdoptar : Button

            init {
                tvNombre = view.findViewById(R.id.tvNombre)
                tvPrecio = view.findViewById(R.id.tvPrecio)
                ivImagen = view.findViewById(R.id.ivImagen)
                tvProducto = view.findViewById(R.id.tvProducto)
                bnAdoptar = view.findViewById(R.id.bnAdoptar)
            }
        }

    //muestra el archivo que se infla y lo muestra
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContenedorDeVista {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_main2, parent, false)

        return ContenedorDeVista(view)
    }

    override fun getItemCount(): Int {
        return inner_animales.size
    }

    //cuando se muestre el elemento, entra a esta funcion, se puede modificar al elemento aqui
    override fun onBindViewHolder(holder: ContenedorDeVista, position: Int) {
        val videojuego: Animal = inner_animales.get(position)

        // videojuego.nombre viene de los elementos que le definimos a nuestro model
        holder.tvNombre.text = videojuego.nombre
        holder.tvProducto.text = videojuego.producto
        holder.tvPrecio.text = videojuego.precio.toString()
        holder.ivImagen.setImageResource(videojuego.imagen)

    }
}

