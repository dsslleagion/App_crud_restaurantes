package com.example.foodflex

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class Restaurant(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nome: String,
    val endereco: String,
    val telefone: String
)