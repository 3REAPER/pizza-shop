package ru.pervukhin.pizzashop.domain

interface CartDishRepository {

    suspend fun getAll(): List<CartDish>

    suspend fun add(dish: Dish)

    suspend fun removeById(id: Int)

    suspend fun plusOneById(id: Int)

    suspend fun minusOneById(id: Int)
}