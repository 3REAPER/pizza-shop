package ru.pervukhin.pizzashop.domain

class CartDish(val id: Int, val name: String, val description: String, val image: String, val price: Int, var count: Int) {

    override fun equals(other: Any?): Boolean {
        return other is CartDish && other.id == id && other.name == name && other.description == description && other.image == image && other.price == price && other.count == count
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + count.hashCode()
        return result
    }
}