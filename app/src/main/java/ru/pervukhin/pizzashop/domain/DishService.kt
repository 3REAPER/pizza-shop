package ru.pervukhin.pizzashop.domain

interface DishService {

    suspend fun fetch(): List<Dish>

    suspend fun getByCategory(category: String): List<Dish>

    suspend fun getFromDataBase(): List<Dish>

    suspend fun save(dishes: List<Dish>)
}