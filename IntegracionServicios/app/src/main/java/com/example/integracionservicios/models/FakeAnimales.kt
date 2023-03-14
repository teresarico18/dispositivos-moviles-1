package com.example.integracionservicios.models

import com.example.integracionservicios.R
import com.example.integracionservicios.adapters.AnimalesAdapter

class FakeAnimales {
    fun getAnimales (): ArrayList<Animal>{
        val animales : ArrayList<Animal>
        animales = arrayListOf<Animal>()

        val animal = Animal(1, "Betty", 100F, "Capibara", R.drawable.capy)

        animales.add(animal)

        animales.add(Animal(2, "Irene", 100F, "Mapache", R.drawable.raccoon))
        animales.add(Animal(3, "James", 100F, "Zorro", R.drawable.fox))
        animales.add(Animal(4, "Nayeon", 100F, "Conejo", R.drawable.bunny))
        animales.add(Animal(5, "Beckett", 100F, "Mara", R.drawable.mara))
        animales.add(Animal(6, "Timon", 100F, "Suricata", R.drawable.suricata))
        animales.add(Animal(7, "Rose", 100F, "Ajolote", R.drawable.axolotl))

        return animales
    }


}