package ru.pervukhin.pizzashop.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartDishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cartDishEntity: CartDishEntity)

    @Query("SELECT * FROM cartDishEntity")
    suspend fun getAll(): List<CartDishEntity>

    @Query("SELECT * FROM cartDishEntity WHERE id= :id")
    suspend fun getById(id: Int): CartDishEntity

    @Query("UPDATE cartDishEntity SET count= count + 1 WHERE id= :id ")
    suspend fun plusOne(id: Int)

    @Query("UPDATE cartDishEntity SET count= count - 1 WHERE id= :id ")
    suspend fun minusOne(id: Int)

    @Query("DELETE FROM cartDishEntity WHERE id= :id")
    suspend fun deleteById(id: Int)
}