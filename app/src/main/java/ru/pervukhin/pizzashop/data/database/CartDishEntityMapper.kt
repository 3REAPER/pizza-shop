package ru.pervukhin.pizzashop.data.database

import ru.pervukhin.pizzashop.domain.CartDish

class CartDishEntityMapper {
    companion object{
        private fun entityToDomain(cartDishEntity: CartDishEntity): CartDish {
            return CartDish(
                cartDishEntity.id,
                cartDishEntity.name,
                cartDishEntity.description,
                cartDishEntity.image,
                cartDishEntity.price,
                cartDishEntity.count)
        }

        fun domainToEntity(cartDish: CartDish): CartDishEntity {
            return CartDishEntity(
                cartDish.id,
                cartDish.name,
                cartDish.description,
                cartDish.image,
                cartDish.price,
                cartDish.count)
        }

        fun listEntityToDomain(cartDishesEntity: List<CartDishEntity>): List<CartDish> {
            var result: List<CartDish> = listOf()
            for (cartDishEntity in cartDishesEntity){
                result = result.plus(entityToDomain(cartDishEntity))
            }
            return result
        }
    }
}