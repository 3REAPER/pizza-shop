package ru.pervukhin.pizzashop.domain

class Dish(val id: Int, val name: String, val description: String, val image: String, val price: Int, val category: String){

    companion object{
        const val CATEGORY_PIZZA = "Пицца"
        const val CATEGORY_COMBO = "Комбо"
        const val CATEGORY_DESSERT = "Десерты"
        const val CATEGORY_BEVERAGES = "Напитки"
    }

    override fun equals(other: Any?): Boolean {
        return other is Dish && other.id == id && other.name == name && other.description == description && other.image == image && other.price == price && other.category == category
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