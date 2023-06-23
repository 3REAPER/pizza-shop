package ru.pervukhin.pizzashop.data.internet

import ru.pervukhin.pizzashop.domain.Dish

class DishDataMapper {
    companion object{
        private fun dataToDomain(dishData: DishData): Dish {
            return Dish(dishData.id, dishData.name, dishData.description, dishData.image, dishData.price,dishData.category)
        }

        fun listDataToDomain(dishesData: List<DishData>): List<Dish>{
            var result: List<Dish> = listOf()
            for (dishData in dishesData){
                result = result.plus(dataToDomain(dishData))
            }
            return result
        }
    }
}