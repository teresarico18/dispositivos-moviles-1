package com.example.linearlayoutactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var bnSuma : Button? = null
    private var bnResta : Button? = null
    private var bnMul : Button? = null
    private var bnDiv : Button? = null
    private var etNumeroPrimero : EditText? = null
    private var etNumeroSegundo : EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarVistas()
        asignarEventos()
    }

    private fun inicializarVistas(){
        bnSuma = findViewById(R.id.bnSuma)
        bnResta = findViewById(R.id.bnResta)
        bnMul = findViewById(R.id.bnMul)
        bnDiv = findViewById(R.id.bnDiv)
        etNumeroPrimero = findViewById(R.id.etNumeroPrimero)
        etNumeroSegundo = findViewById(R.id.etNumeroSegundo)
    }

    private fun asignarEventos(){
        bnSuma?.setOnClickListener(this)
        bnResta?.setOnClickListener(this)
        bnMul?.setOnClickListener(this)
        bnDiv?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val numeroUno = etNumeroPrimero?.text.toString().toFloatOrNull()
        val numeroDos = etNumeroSegundo?.text.toString().toFloatOrNull()
        val aritmetica = Aritmetica()
        when(p0?.id){
            R.id.bnSuma -> {
                if (numeroUno != null && numeroDos != null) {
                    val suma = aritmetica.suma(numeroUno, numeroDos)
                    val mensaje = Mensajes("La sumatoria fue $suma", this)
                    mensaje.mostrarToast()
                }
            }
            R.id.bnResta -> {
                if (numeroUno != null && numeroDos != null) {
                    val resta = aritmetica.resta(numeroUno, numeroDos)
                    val mensaje = Mensajes("La resta fue $resta", this)
                    mensaje.mostrarSnackbar(p0)
                }
            }
            R.id.bnMul -> {
                if (numeroUno != null && numeroDos != null) {
                    val mul = aritmetica.mul(numeroUno, numeroDos)
                    val mensaje = Mensajes("La multiplicacion fue $mul", this)
                    mensaje.mostrarToast()
                }
            }
            R.id.bnDiv -> {
                if (numeroUno != null && numeroDos != null) {
                    val div = aritmetica.div(numeroUno, numeroDos)
                    val mensaje = Mensajes("La division fue $div", this)
                    mensaje.mostrarSnackbar(p0)
                }
            }
        }
    }


}