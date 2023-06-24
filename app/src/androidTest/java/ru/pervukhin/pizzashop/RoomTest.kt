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
import ru.pervukhin.pizzashop.data.database.AppDataBase
import ru.pervukhin.pizzashop.data.database.DishDao
import ru.pervukhin.pizzashop.data.database.DishEntity
import ru.pervukhin.pizzashop.domain.Dish
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RoomTest {
    private lateinit var db: AppDataBase
    private lateinit var dao: DishDao

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.getDishDao()
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
                val list: List<Dish> = listOf()
                assertEquals(list, dao.getAll())
            }
        }
    }

    @Test
    fun testInsert() {
        runBlocking {
            launch {
                val dish1 = DishEntity(1,"test", "test", "test", 1, "test")
                dao.insert(dish1)
                assertEquals(dish1, dao.getAll().get(0))
            }
        }
    }

    @Test
    fun testGetById() {
        runBlocking {
            launch {
                val dish1 = DishEntity(1,"test", "test", "test", 1, "test")
                val dish2 = DishEntity(2,"test", "test", "test", 1, "test")
                dao.insert(dish1)
                dao.insert(dish2)
                assertEquals(dish1, dao.getById(1))
            }
        }
    }

    @Test
    fun testGetAll() {
        runBlocking {
            launch {
                val dish1 = DishEntity(1,"test", "test", "test", 1, "test")
                val dish2 = DishEntity(2,"test", "test", "test", 1, "test")
                dao.insert(dish1)
                dao.insert(dish2)
                assertEquals(listOf(dish1,dish2), dao.getAll())
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
                dao.insert(dish1)
                dao.insert(dish2)
                dao.insert(dish3)
                assertEquals(listOf(dish1,dish3), dao.getByCategory("Пицца"))
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
                dao.insert(dish1)
                dao.insert(dish2)
                dao.deleteAll()
                assertEquals(list, dao.getAll())
            }
        }
    }

    @Test
    fun testDeleteById() {
        runBlocking {
            launch {
                val dish1 = DishEntity(1,"test", "test", "test", 1, "test")
                val dish2 = DishEntity(2,"test", "test", "test", 1, "test")
                dao.insert(dish1)
                dao.insert(dish2)
                dao.deleteById(1)
                assertEquals(dish2, dao.getAll().get(0))
            }
        }
    }
}