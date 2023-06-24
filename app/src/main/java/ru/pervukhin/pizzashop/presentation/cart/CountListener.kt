package ru.pervukhin.pizzashop.presentation.cart

import ru.pervukhin.pizzashop.domain.CartDish

interface CountListener {

    fun onPlusClick(cartDish: CartDish)

    fun onMinusClick(cartDish: CartDish)

    fun onRemoveItem(cartDish: CartDish)
}