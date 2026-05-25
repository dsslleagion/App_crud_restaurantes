package com.example.foodflex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodflex.databinding.ItemRestaurantBinding

class RestaurantAdapter(
    private val lista: List<Restaurant>,
    private val onDelete: (Restaurant) -> Unit
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    inner class RestaurantViewHolder(val binding: ItemRestaurantBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {

        val binding = ItemRestaurantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {

        val restaurant = lista[position]

        holder.binding.txtNome.text = restaurant.nome
        holder.binding.txtEndereco.text = restaurant.endereco
        holder.binding.txtTelefone.text = restaurant.telefone

        holder.binding.btnExcluir.setOnClickListener {
            onDelete(restaurant)
        }
    }

    override fun getItemCount() = lista.size
}