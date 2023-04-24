package com.example.clase05persistenciadatossqlite.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clase05persistenciadatossqlite.R
import com.example.clase05persistenciadatossqlite.db.ManejadorBaseDatos
import com.example.clase05persistenciadatossqlite.interfaces.albumesInterface
import com.example.clase05persistenciadatossqlite.modelos.Album

class AlbumesAdapter(albumes: ArrayList<Album>, contexto: Context, albumesInterface: albumesInterface) :
    RecyclerView.Adapter<AlbumesAdapter.ContenedorDeVista>() {
    var inner_albumes: ArrayList<Album> = albumes
    var inner_context: Context = contexto
    var albumesInterface = albumesInterface

    inner class ContenedorDeVista(view: View) :
        RecyclerView.ViewHolder(view) {
        val tvTitle: TextView
        val etPrecio: TextView
        val tvContent: TextView
        val img01: ImageView
        val img02: ImageView

        init {
            tvTitle = view.findViewById(R.id.tvTitle)
            etPrecio = view.findViewById(R.id.etPrecio)
            tvContent = view.findViewById(R.id.tvContent)
            img01 = view.findViewById(R.id.img01)
            img02 = view.findViewById(R.id.img02)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContenedorDeVista {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_album, parent, false)
        return ContenedorDeVista(view)
    }

    override fun getItemCount(): Int {
        return inner_albumes.size
    }

    override fun onBindViewHolder(holder: ContenedorDeVista, position: Int) {
        val album: Album = inner_albumes.get(position)
        holder.img02.setOnClickListener {
            val baseDatos = ManejadorBaseDatos(inner_context!!)
            val argumentosWhere = arrayOf(album.id.toString())
            baseDatos.eliminar("id = ? ", argumentosWhere)
            albumesInterface.albumEliminado()
        }

        holder.img01.setOnClickListener {
            albumesInterface.editarAlbum(album)
        }
        holder.tvTitle.text = album.nombre
        holder.etPrecio.text = album.precio.toString()
        holder.tvContent.text = album.genero
    }
}

/*class AlbumesAdapter(contexto: Context, var listaDeAlbumes: ArrayList<Album>, albumInterface: albumesInterface) : BaseAdapter() {

    var contexto: Context? = contexto
    var albumInterface: albumesInterface? = albumInterface
    override fun getCount(): Int {
        return listaDeAlbumes.size
    }

    override fun getItem(p0: Int): Any {
        return listaDeAlbumes[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        //usar vista reciclado para eficientar
        var convertView: View?= p1
        if(convertView == null){
            convertView = View.inflate(contexto, R.layout.row_album, null)
        }

        val album = listaDeAlbumes[p0]

        val miVista = convertView!!
        val tvTitle: TextView = miVista.findViewById(R.id.etTitle)
        val tvContent: TextView = miVista.findViewById(R.id.tvContent)
        val img01: ImageView = miVista.findViewById(R.id.img01)
        val img02: ImageView = miVista.findViewById(R.id.img02)
        tvTitle.text = album.nombre
        tvContent.text = album.genero
        //borrar
        img02.setOnClickListener(){
            //eliminar
            val baseDatos = ManejadorBaseDatos(this.contexto!!)
            val argumentosWhere = arrayOf(album.id.toString())
            baseDatos.eliminar("id = ? ", argumentosWhere)
            albumInterface?.albumEliminado()
        }

        img01.setOnClickListener(){
            //Editar
            albumInterface?.editarAlbum(album)
        }

        return miVista
    }

}*/
