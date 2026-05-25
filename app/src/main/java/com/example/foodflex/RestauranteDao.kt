package com.example.foodflex

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RestaurantDao {

    @Insert
    suspend fun inserir(restaurant: Restaurant)

    @Query("SELECT * FROM restaurants")
    suspend fun listar(): List<Restaurant>

    @Delete
    suspend fun deletar(restaurant: Restaurant)
}