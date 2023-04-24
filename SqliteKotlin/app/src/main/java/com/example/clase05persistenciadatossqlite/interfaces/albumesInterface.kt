package com.example.clase05persistenciadatossqlite.interfaces

import com.example.clase05persistenciadatossqlite.modelos.Album

public interface albumesInterface {
    fun albumEliminado()
    fun editarAlbum(album: Album)

}