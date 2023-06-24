package ru.pervukhin.pizzashop.data.internet

import retrofit2.http.GET

interface DishService {
    @GET("gi5Gys")
    suspend fun getAll(): List<DishData>
}