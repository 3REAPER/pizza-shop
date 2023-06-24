package ru.pervukhin.pizzashop.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DishEntity::class, CartDishEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getDishDao(): DishDao

    abstract fun getCartDishDao(): CartDishDao
}