package com.example.retrofit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.adapters.PerrosAdapter
import com.example.retrofit.models.ImageRandom
import com.example.retrofit.models.ImagesBreed
import com.example.retrofit.network.API
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var tvLink : TextView
    private lateinit var ivImagen : ImageView
    private lateinit var raza: String
    private val LINK_KEY = "link"
    private var link: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("PREFERENCIAS", "onCreate")
        setContentView(R.layout.activity_main)
        val apiCall = API().crearServicioAPI()
        ivImagen = findViewById(R.id.ivImagen)
        tvLink = findViewById(R.id.tvLink)
        //Obtener link guardado
        val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
        link = miSharedPreferences.getString(LINK_KEY, "").toString()
        Log.d("LINKGUARDADO", "$link")

        if(connectToInternet()){
            apiCall.imagenAleatoria().enqueue(object : Callback<ImageRandom> {
                override fun onResponse(call: Call<ImageRandom>, response: Response<ImageRandom>) {
                    Log.d("API_PRUEBA", "status es " + response.body()?.status)
                    Log.d("API_PRUEBA ", "message es " + response.body()?.message)
                    Picasso.get().load(response.body()?.message).into(ivImagen)
                    tvLink.text = response.body()?.message
                    val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
                    val editor = miSharedPreferences.edit()
                    editor.putString(LINK_KEY, response.body()?.message)
                    editor.apply()
                    val link2 = miSharedPreferences.getString(LINK_KEY, "").toString()
                    Log.d("LINKNUEVO", "$link2")
                    Toast.makeText(this@MainActivity, "Conectado a api", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<ImageRandom>, t: Throwable) {
                    Toast.makeText( this@MainActivity, "No fue posible conectar a API", Toast.LENGTH_SHORT ).show()
                }
            })
        }else{
            Toast.makeText(this, "No tienes internet", Toast.LENGTH_LONG).show()
            Picasso.get().load(link).into(ivImagen)
            tvLink.text = "Te guardamos este bello perrito\n" + link
        }

        val spRazas = findViewById<Spinner>(R.id.spRazas)
        spRazas.setOnItemSelectedListener(this)

    }
    override fun onResume() {
        Log.d("PREFERENCIAS", "onResume")
        if(TextUtils.isEmpty(link)){
            val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
            link = miSharedPreferences.getString(LINK_KEY, "").toString()
        }
        super.onResume()
    }

    override fun onStart() {
        Log.d("PREFERENCIAS", "onStart")
        super.onStart()
    }

    override fun onPause() {
        Log.d("PREFERENCIAS", "onPause")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("PREFERENCIAS", "onDestroy")
        super.onDestroy()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.option_menu_list_images) {
            Toast.makeText(this, "Option menu 1", Toast.LENGTH_SHORT).show()
            val apiCall = API().crearServicioAPI()
            apiCall.listaImagenesDePerrosPorRaza("beagle").enqueue(object: Callback<ImagesBreed>{
                override fun onResponse(call: Call<ImagesBreed>, response: Response<ImagesBreed>) {
                    val dogs = response.body()?.message
                    //response.body()?.status
                    Log.d("PRUEBAS", "Status de la respuesta ${response.body()?.status}")
                    if(dogs != null){
                        for (dog in dogs){
                            Log.d("PRUEBAS", "Perro es $dog")
                        }
                    }
                }

                override fun onFailure(call: Call<ImagesBreed>, t: Throwable) {
                    //
                }
            })
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_images, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        raza = p0?.getItemAtPosition(p2).toString()
        Log.d("RAZA", raza)
        val apiCall = API().crearServicioAPI()
        apiCall.listaImagenesDePerrosPorRaza(raza).enqueue(object: Callback<ImagesBreed>{
            override fun onResponse(call: Call<ImagesBreed>, response: Response<ImagesBreed>) {
                val dogs = response.body()?.message
                //response.body()?.status
                Log.d("PERROS", "Status de la respuesta ${response.body()?.status}")
                if(dogs != null){
                    val recycler = findViewById<RecyclerView>(R.id.recyclerPerros)
                    val administradorDeLayouts = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                    recycler.layoutManager = administradorDeLayouts
                    recycler.adapter = PerrosAdapter(dogs, this@MainActivity)
                    for (dog in dogs){
                        Log.d("PERROS", "Perro es $dog")
                    }
                }
            }

            override fun onFailure(call: Call<ImagesBreed>, t: Throwable) {
                Log.d("TEST", "test")
            }})
    }
    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(this, "Skip", Toast.LENGTH_LONG).show()
    }
    fun connectToInternet(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

}