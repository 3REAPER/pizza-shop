package ru.pervukhin.pizzashop.data.database

import ru.pervukhin.pizzashop.domain.Dish

class DishEntityMapper {

    companion object{
        private fun entityToDomain(dishEntity: DishEntity): Dish {
            return Dish(
                dishEntity.id,
                dishEntity.name,
                dishEntity.description,
                dishEntity.image,
                dishEntity.price,
                dishEntity.category)
        }

        fun domainToEntity(dish: Dish): DishEntity {
            return DishEntity(
                dish.id,
                dish.name,
                dish.description,
                dish.image,
                dish.price,
                dish.category)
        }

        fun listEntityToDomain(dishesEntity: List<DishEntity>): List<Dish> {
            var result: List<Dish> = listOf()
            for (dishEntity in dishesEntity){
                result = result.plus(entityToDomain(dishEntity))
            }
            return result
        }

        fun listDomainToEntity(dishes: List<Dish>): List<DishEntity> {
            var result: List<DishEntity> = listOf()
            for (dish in dishes){
                result = result.plus(domainToEntity(dish))
            }
            return result
        }
    }
}