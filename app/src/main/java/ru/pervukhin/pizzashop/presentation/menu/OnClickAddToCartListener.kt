package ru.pervukhin.pizzashop.presentation.menu

import ru.pervukhin.pizzashop.domain.Dish

interface OnClickAddToCartListener {

    fun onClickAddToCart(dish: Dish)
}