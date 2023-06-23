package ru.pervukhin.pizzashop.data.internet

import com.google.gson.annotations.SerializedName

class DishData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_url")
    val image: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("category")
    val category: String)