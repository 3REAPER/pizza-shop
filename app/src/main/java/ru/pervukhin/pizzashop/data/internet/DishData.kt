package ru.pervukhin.pizzashop.data.internet

import com.google.gson.annotations.SerializedName
import ru.pervukhin.pizzashop.domain.Dish

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
    val category: String){

    override fun equals(other: Any?): Boolean {
        return other is DishData && other.id == id && other.name == name && other.description == description && other.image == image && other.price == price && other.category == category
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + category.hashCode()
        return result
    }
}