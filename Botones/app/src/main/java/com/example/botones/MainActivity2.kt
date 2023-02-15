package com.example.botones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity2 : AppCompatActivity() {
    private var cambioIcono : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var fraseRecibida = intent.getStringExtra("frase")
        var edad = intent.getIntExtra("edad", 0)

        Log.d("Datos", "Datos recibidos con éxito fueron frase $fraseRecibida y $edad")

        var btnAbrirPantalla = findViewById<Button>(R.id.btnPantalla)
        var ivLogo = findViewById<ImageView>(R.id.ivLogo)
        ivLogo.setOnClickListener{
            var arregloImagenes = arrayOf<Int>(R.drawable.anahuac, R.drawable.iest)
            if (cambioIcono) {
                ivLogo.setImageResource(arregloImagenes[0])
            }else{
                ivLogo.setImageResource(arregloImagenes[1])
            }
            cambioIcono = !cambioIcono
        }

        btnAbrirPantalla.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
        var btnCerrar = findViewById<Button>(R.id.btnCerrar)
        btnCerrar.setOnClickListener{finish();}
    }

}