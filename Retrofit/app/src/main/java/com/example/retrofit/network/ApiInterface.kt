package com.example.retrofit.network

import com.example.retrofit.models.ImageRandom
import com.example.retrofit.models.ImagesBreed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("breeds/image/random")
    fun imagenAleatoria(): Call<ImageRandom>

    @GET("breed/{raza}/images")
    fun listaImagenesDePerrosPorRaza
                (@Path("raza") raza: String): Call<ImagesBreed>
    //si raza = "chihuahua url seria
}