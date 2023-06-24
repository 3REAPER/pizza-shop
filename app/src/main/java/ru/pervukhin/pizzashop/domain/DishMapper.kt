package ru.pervukhin.pizzashop.domain

import ru.pervukhin.pizzashop.domain.CartDish
import ru.pervukhin.pizzashop.domain.Dish

class DishMapper {
    companion object{
        fun dishToCart(dish: Dish): CartDish{
            return CartDish(
                dish.id,
                dish.name,
                dish.description,
                dish.image,
                dish.price,
                1
            )
        }
    }
}