package com.example.integracionservicios.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.integracionservicios.ListaAnimales
import com.example.integracionservicios.R
import com.example.integracionservicios.models.Animal

class AnimalesAdapter(videojuegos: ArrayList<Animal>, context: Context) :
    RecyclerView.Adapter<AnimalesAdapter.ContenedorDeVista> (){
    var inner_animales: ArrayList<Animal> = videojuegos
    var inner_context: Context = context

    inner class ContenedorDeVista(view: View) : // : siginifca heredar propiedades del viewHolder
        RecyclerView.ViewHolder(view), View.OnClickListener {
            //aqui haremos el inflate del layout
            val tvNombre : TextView
            val tvPrecio : TextView
            val ivImagen : ImageView
            val tvProducto : TextView
            val tvClasificacion : TextView
            val bnAdoptar : Button

            init {
                tvNombre = view.findViewById(R.id.tvNombre)
                tvPrecio = view.findViewById(R.id.tvPrecio)
                ivImagen = view.findViewById(R.id.ivImagen)
                tvProducto = view.findViewById(R.id.tvProducto)
                tvClasificacion = view.findViewById(R.id.tvClasificacion)
                bnAdoptar = view.findViewById(R.id.bnAdoptar)

                bnAdoptar.setOnClickListener(this)
            }

        override fun onClick(p0: View?) {
            if(adapterPosition>=0){
                //Necesario usar contexto
                val miSharedPreferences = inner_context.getSharedPreferences("PERSISTENCIA",
                    AppCompatActivity.MODE_PRIVATE
                )
                val edad = miSharedPreferences.getInt("edad", 0)

                val animal: Animal = inner_animales.get(adapterPosition)
                if ((animal.clasificacion=="R" && edad < 18) || (animal.clasificacion == "T" || animal.clasificacion == "R") && edad < 5){
                    Toast.makeText(inner_context, "No puedes adoptar a ${animal.nombre}.", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(inner_context, "${animal.nombre} ha sido adoptada!", Toast.LENGTH_LONG).show()

                }
                //val activity = inner_context as Activity
                //val intent = Intent(activity, ListaAnimales::class.java)
                //activity.startActivity(intent)
            }
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
        val animal: Animal = inner_animales.get(position)

        // videojuego.nombre viene de los elementos que le definimos a nuestro model
        holder.tvNombre.text = animal.nombre
        holder.tvProducto.text = animal.producto
        holder.tvPrecio.text = animal.precio.toString()
        holder.ivImagen.setImageResource(animal.imagen)
        holder.tvClasificacion.text = animal.clasificacion
    }
}

