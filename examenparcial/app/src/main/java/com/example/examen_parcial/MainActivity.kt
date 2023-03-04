package com.example.examen_parcial

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var horario: String
    private lateinit var tvMovie: TextView
    private lateinit var tvCerrar: TextView
    private lateinit var ivPoster : ImageView
    private var cambioIcono : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bnComprar = findViewById<Button>(R.id.bnComprar)
        tvCerrar = findViewById<Button>(R.id.tvCerrar)
        tvMovie = findViewById(R.id.tvPelicula)
        val spHorarios = findViewById<Spinner>(R.id.spHorarios)
        spHorarios.setOnItemSelectedListener(this)

        bnComprar.setOnClickListener{
            if( horario == "15:00 SUB" || horario == "17:00 3D ESP" || horario == "19:00 ESP"){
                Snackbar.make(bnComprar, "Lo sentimos se han agotado las entradas", Snackbar.LENGTH_LONG).show()
            } else {
                val i = Intent(this, MainActivity2::class.java)
                i.putExtra("horario", horario)
                i.putExtra("pelicula", tvMovie.text.toString())
                startActivity(i)
            }

        }

        ivPoster = findViewById(R.id.ivPoster)
        ivPoster.setOnClickListener{
            val arregloImagenes = arrayOf<Int>(R.drawable.about_time, R.drawable.poster)
            if (cambioIcono) {
                ivPoster.setImageResource(arregloImagenes[0])
            }else{
                ivPoster.setImageResource(arregloImagenes[1])
            }
            cambioIcono = !cambioIcono
        }
        tvCerrar.setOnClickListener{
            finish()
        }

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        horario = p0?.getItemAtPosition(p2).toString()

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}

