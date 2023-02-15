package com.example.relojspinners

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.relojspinners.databinding.ActivityMainBinding

class MainActivity : Activity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private var numSeleccionado1: String? = null
    private var numSeleccionado2: String? = null
    private var numMayor: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bnCalcular.text = "Calcular"
        val adaptadorsp1 = ArrayAdapter.createFromResource(this, R.array.numeros, android.R.layout.simple_spinner_item)
        adaptadorsp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spNum1.adapter = adaptadorsp1
        binding.spNum1.onItemSelectedListener = this

        val adaptadorsp2 = ArrayAdapter.createFromResource(this, R.array.numeros, android.R.layout.simple_spinner_item)
        adaptadorsp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spNum2.adapter = adaptadorsp2
        binding.spNum2.onItemSelectedListener = this

        binding.bnCalcular.setOnClickListener{
            val alerta = AlertDialog.Builder(this)
            if (Integer.valueOf(numSeleccionado1!!) >= Integer.valueOf(numSeleccionado2!!)) {
                numMayor = numSeleccionado1
            } else if (Integer.valueOf(numSeleccionado2!!) > Integer.valueOf(numSeleccionado1!!)) {
                numMayor = numSeleccionado2
            }
            alerta.setTitle("Calcular")
                .setMessage("El numero mayor es: $numMayor")
                .setCancelable(false)
                .setPositiveButton("Ok", DialogInterface.OnClickListener(){
                    dialogInterface, i ->   Toast.makeText(this, "Gracias por participar", Toast.LENGTH_LONG).show()
                })
                .show()

        }
    }

    override fun onItemSelected(vistaPadre: AdapterView<*>?, vistaRow: View?, posicion: Int, idVista: Long) {
        if (vistaPadre!!.id == R.id.spNum1) {
            numSeleccionado1 = vistaPadre.getItemAtPosition(posicion).toString()
        } else if (vistaPadre!!.id == R.id.spNum2) {
            numSeleccionado2 = vistaPadre.getItemAtPosition(posicion).toString()
        }
    }
    override fun onNothingSelected(p0: AdapterView<*>?) {
        //TODO("Not yet implemented")
    }

}