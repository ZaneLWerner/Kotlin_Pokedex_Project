package com.example.learnkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.learnkotlin.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}
var client = OkHttpClient()

var gson = Gson()

interface PokemonService {

    @GET("api/v2/pokemon/{name}/")
    fun listPokemon(@Path("name") name: String?): Call<Poke>
}
//https://pokeapi.co/api/v2/pokemon/{id or name}/
var retrofit: Retrofit = Builder()
    .baseUrl("https://pokeapi.co/")
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(client)
    .build()

var service: PokemonService = retrofit.create(PokemonService::class.java)

fun main(){
    //user input
    val apiCall: Call<Poke> = service.listPokemon("user input")

    print(apiCall)
}
