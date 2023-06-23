package ru.pervukhin.pizzashop.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DishDao {

    @Insert
    suspend fun insert(dishEntity: DishEntity)

    @Query("SELECT * FROM dishEntity")
    suspend fun getAll(): List<DishEntity>

    @Query("SELECT * FROM dishEntity WHERE id= :id")
    suspend fun getById(id: Int): DishEntity

    @Query("SELECT * FROM dishEntity WHERE category= :category")
    suspend fun getByCategory(category: String): List<DishEntity>

    @Query("DELETE FROM dishEntity")
    suspend fun deleteAll()

    @Query("DELETE FROM dishEntity WHERE id= :id")
    suspend fun deleteById(id: Int)
}