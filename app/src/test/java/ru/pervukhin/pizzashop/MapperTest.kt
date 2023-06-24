package ru.pervukhin.pizzashop

import org.junit.Assert
import org.junit.Test
import org.w3c.dom.Entity
import ru.pervukhin.pizzashop.data.database.CartDishEntity
import ru.pervukhin.pizzashop.data.database.CartDishEntityMapper
import ru.pervukhin.pizzashop.data.database.DishEntity
import ru.pervukhin.pizzashop.data.database.DishEntityMapper
import ru.pervukhin.pizzashop.data.internet.DishData
import ru.pervukhin.pizzashop.data.internet.DishDataMapper
import ru.pervukhin.pizzashop.domain.CartDish
import ru.pervukhin.pizzashop.domain.Dish

class MapperTest {
    @Test
    fun testListEntityToDomain(){
        val dishEntity1 = DishEntity(1,"2", "3","4", 5,"6")
        val dishEntity2 = DishEntity(2,"7", "8","9", 10,"11")
        val expected: List<Dish> = listOf(Dish(1,"2", "3","4", 5,"6"), Dish(2,"7", "8","9", 10,"11"))
        Assert.assertEquals(expected, DishEntityMapper.listEntityToDomain(listOf(dishEntity1, dishEntity2)))
    }

    @Test
    fun testDomainToEntity(){
        val dish = Dish(1,"2", "3","4", 5,"6")
        val expected = DishEntity(1,"2", "3","4", 5,"6")
        Assert.assertEquals(expected, DishEntityMapper.domainToEntity(dish))
    }

    @Test
    fun testListDataToDomain(){
        val dishData1 = DishData(1,"2", "3","4", 5,"6")
        val dishData2 = DishData(2,"7", "8","9", 10,"11")
        val expected: List<Dish> = listOf(Dish(1,"2", "3","4", 5,"6"), Dish(2,"7", "8","9", 10,"11"))
        Assert.assertEquals(expected, DishDataMapper.listDataToDomain(listOf(dishData1, dishData2)))
    }

    @Test
    fun testListCartEntityToDomain(){
        val dishEntity1 = CartDishEntity(1,"2", "3","4", 5,6)
        val dishEntity2 = CartDishEntity(2,"7", "8","9", 10,11)
        val expected: List<CartDish> = listOf(CartDish(1,"2", "3","4", 5,6), CartDish(2,"7", "8","9", 10,11))
        Assert.assertEquals(expected, CartDishEntityMapper.listEntityToDomain(listOf(dishEntity1, dishEntity2)))
    }

    @Test
    fun testDomainToCartEntity(){
        val dish = CartDish(1,"2", "3","4", 5,6)
        val expected = CartDishEntity(1,"2", "3","4", 5,6)
        Assert.assertEquals(expected, CartDishEntityMapper.domainToEntity(dish))
    }
}