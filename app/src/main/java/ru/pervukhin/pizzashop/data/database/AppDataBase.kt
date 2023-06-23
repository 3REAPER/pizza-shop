package ru.pervukhin.pizzashop.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DishEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getDishDao(): DishDao
}