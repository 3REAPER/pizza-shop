package ru.pervukhin.pizzashop.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DishEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val price: Int,
    val category: String
)