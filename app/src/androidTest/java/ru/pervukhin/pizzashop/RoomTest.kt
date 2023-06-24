package ru.pervukhin.pizzashop

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.pervukhin.pizzashop.data.database.*
import ru.pervukhin.pizzashop.domain.Dish
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RoomTest {
    private lateinit var db: AppDataBase
    private lateinit var dishDao: DishDao
    private lateinit var cartDishDao: CartDishDao

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        dishDao = db.getDishDao()
        cartDishDao = db.getCartDishDao()
    }

    @After
    @Throws(IOException::class)
    fun clear() {
        db.close()
    }

    @Test
    fun testEmptyList() {
        runBlocking {
            launch {
                val list: List<DishEntity> = listOf()
                assertEquals(list, dishDao.getAll())
            }
        }
    }

    @Test
    fun testInsert() {
        runBlocking {
            launch {
                val dish1 = DishEntity(1,"test", "test", "test", 1, "test")
                dishDao.insert(dish1)
                assertEquals(dish1, dishDao.getAll().get(0))
            }
        }
    }

    @Test
    fun testGetById() {
        runBlocking {
            launch {
                val dish1 = DishEntity(1,"test", "test", "test", 1, "test")
                val dish2 = DishEntity(2,"test", "test", "test", 1, "test")
                dishDao.insert(dish1)
                dishDao.insert(dish2)
                assertEquals(dish1, dishDao.getById(1))
            }
        }
    }

    @Test
    fun testGetAll() {
        runBlocking {
            launch {
                val dish1 = DishEntity(1,"test", "test", "test", 1, "test")
                val dish2 = DishEntity(2,"test", "test", "test", 1, "test")
                dishDao.insert(dish1)
                dishDao.insert(dish2)
                assertEquals(listOf(dish1,dish2), dishDao.getAll())
            }
        }
    }


    @Test
    fun testGetByCategory() {
        runBlocking {
            launch {
                val dish1 = DishEntity(1,"test", "test", "test", 1, "Пицца")
                val dish2 = DishEntity(2,"test", "test", "test", 1, "test")
                val dish3 = DishEntity(3,"test", "test", "test", 1, "Пицца")
                dishDao.insert(dish1)
                dishDao.insert(dish2)
                dishDao.insert(dish3)
                assertEquals(listOf(dish1,dish3), dishDao.getByCategory("Пицца"))
            }
        }
    }

    @Test
    fun testDeleteAll() {
        runBlocking {
            launch {
                val list: List<Dish> = listOf()
                val dish1 = DishEntity(1,"test", "test", "test", 1, "test")
                val dish2 = DishEntity(2,"test", "test", "test", 1, "test")
                dishDao.insert(dish1)
                dishDao.insert(dish2)
                dishDao.deleteAll()
                assertEquals(list, dishDao.getAll())
            }
        }
    }

    @Test
    fun testDeleteById() {
        runBlocking {
            launch {
                val dish1 = DishEntity(1,"test", "test", "test", 1, "test")
                val dish2 = DishEntity(2,"test", "test", "test", 1, "test")
                dishDao.insert(dish1)
                dishDao.insert(dish2)
                dishDao.deleteById(1)
                assertEquals(dish2, dishDao.getAll().get(0))
            }
        }
    }

    @Test
    fun testEmptyListCart() {
        runBlocking {
            launch {
                val list: List<CartDishEntity> = listOf()
                assertEquals(list, cartDishDao.getAll())
            }
        }
    }


    @Test
    fun testInsertCart() {
        runBlocking {
            launch {
                val cartDish = CartDishEntity(1,"test", "test", "test", 1, 1)
                cartDishDao.insert(cartDish)
                assertEquals(cartDish, cartDishDao.getAll().get(0))
            }
        }
    }

    @Test
    fun testGetByIdCart() {
        runBlocking {
            launch {
                val cartDish1 = CartDishEntity(1,"test", "test", "test", 1, 1)
                val cartDish2 = CartDishEntity(2,"test", "test", "test", 1, 1)
                cartDishDao.insert(cartDish1)
                cartDishDao.insert(cartDish2)
                assertEquals(cartDish1, cartDishDao.getById(1))
            }
        }
    }

    @Test
    fun testGetAllCart() {
        runBlocking {
            launch {
                val cartDish1 = CartDishEntity(1,"test", "test", "test", 1, 1)
                val cartDish2 = CartDishEntity(2,"test", "test", "test", 1, 1)
                cartDishDao.insert(cartDish1)
                cartDishDao.insert(cartDish2)
                assertEquals(listOf(cartDish1,cartDish2), cartDishDao.getAll())
            }
        }
    }

    @Test
    fun testDeleteByIdCart() {
        runBlocking {
            launch {
                val cartDish1 = CartDishEntity(1,"test", "test", "test", 1, 1)
                val cartDish2 = CartDishEntity(2,"test", "test", "test", 1, 1)
                cartDishDao.insert(cartDish1)
                cartDishDao.insert(cartDish2)
                cartDishDao.deleteById(1)
                assertEquals(cartDish2, cartDishDao.getAll().get(0))
            }
        }
    }

    @Test
    fun testPlusOne() {
        runBlocking {
            launch {
                val cartDish = CartDishEntity(1,"test", "test", "test", 1, 1)
                cartDishDao.insert(cartDish)
                cartDishDao.plusOne(cartDish.id)
                assertEquals(cartDish.count + 1, cartDishDao.getById(cartDish.id).count)
            }
        }
    }

    @Test
    fun testMinusOne() {
        runBlocking {
            launch {
                val cartDish = CartDishEntity(1,"test", "test", "test", 1, 2)
                cartDishDao.insert(cartDish)
                cartDishDao.minusOne(cartDish.id)
                assertEquals(cartDish.count - 1, cartDishDao.getById(cartDish.id).count)
            }
        }
    }
}