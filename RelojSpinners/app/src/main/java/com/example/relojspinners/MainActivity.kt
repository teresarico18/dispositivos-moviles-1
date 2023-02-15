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
    lateinit var spNum1: Spinner
    lateinit var spNum2: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bnCalcular.text = "Calcular"
        val adaptador = ArrayAdapter.createFromResource(this, R.array.numeros, android.R.layout.simple_spinner_item)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spNum1 = findViewById(R.id.sp_num1)
        numSeleccionado1 = spNum1.getSelectedItem().toString();
        spNum2 = findViewById(R.id.sp_num2)
        numSeleccionado2 = spNum2.getSelectedItem().toString();

        binding.bnCalcular.setOnClickListener{
            val alerta = AlertDialog.Builder(this)
            alerta.setTitle("Calcular")
                .setMessage("Miau, $numSeleccionado1, $numSeleccionado2")
                .setCancelable(false)
                .setPositiveButton("Ok", DialogInterface.OnClickListener(){
                    dialogInterface, i ->   Toast.makeText(this, "Una lastima:(", Toast.LENGTH_LONG).show()
                })
                .show()

        }
    }


}