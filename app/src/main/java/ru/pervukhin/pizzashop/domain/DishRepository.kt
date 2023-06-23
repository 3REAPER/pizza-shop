package ru.pervukhin.pizzashop.domain

interface DishRepository {

    suspend fun getAllFromDataBase(): List<Dish>

    suspend fun getAllFromInternet(): List<Dish>

    suspend fun save(dishes: List<Dish>)
}