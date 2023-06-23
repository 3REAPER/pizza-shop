package ru.pervukhin.pizzashop.data.internet

import retrofit2.http.GET

interface DishService {
    @GET("NDN3Na")
    suspend fun getAll(): List<DishData>
}