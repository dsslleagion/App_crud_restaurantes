package com.example.foodflex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.foodflex.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "restaurant-db"
        ).build()

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this)

        carregarLista()

        binding.btnSalvar.setOnClickListener {

            val nome = binding.edtNome.text.toString()
            val endereco = binding.edtEndereco.text.toString()
            val telefone = binding.edtTelefone.text.toString()

            val restaurant = Restaurant(
                nome = nome,
                endereco = endereco,
                telefone = telefone
            )

            CoroutineScope(Dispatchers.IO).launch {

                db.restaurantDao().inserir(restaurant)

                withContext(Dispatchers.Main) {

                    binding.edtNome.text.clear()
                    binding.edtEndereco.text.clear()
                    binding.edtTelefone.text.clear()

                    carregarLista()
                }
            }
        }
    }

    private fun carregarLista() {

        CoroutineScope(Dispatchers.IO).launch {

            val lista = db.restaurantDao().listar()

            withContext(Dispatchers.Main) {

                binding.recyclerView.adapter =
                    RestaurantAdapter(lista) { restaurant ->

                        CoroutineScope(Dispatchers.IO).launch {

                            db.restaurantDao().deletar(restaurant)

                            carregarLista()
                        }
                    }
            }
        }
    }
}