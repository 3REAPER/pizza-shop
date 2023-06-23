package ru.pervukhin.pizzashop.data

import kotlinx.coroutines.runBlocking
import ru.pervukhin.pizzashop.data.database.DishDao
import ru.pervukhin.pizzashop.data.database.DishEntityMapper
import ru.pervukhin.pizzashop.data.internet.DishDataMapper
import ru.pervukhin.pizzashop.data.internet.DishService
import ru.pervukhin.pizzashop.domain.Dish
import ru.pervukhin.pizzashop.domain.DishRepository
import ru.pervukhin.pizzashop.domain.InternetConnection
import ru.pervukhin.pizzashop.presentation.App
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class DishRepositoryImpl: DishRepository {
    @Inject
    lateinit var dishService: DishService
    @Inject
    lateinit var dishDao: DishDao

    init {
        App.appComponent.inject(this)
    }

    override suspend fun getAllFromDataBase(): List<Dish> {
        return DishEntityMapper.listEntityToDomain(dishDao.getAll())
    }

    override suspend fun getAllFromInternet(): List<Dish> {
        DishDataMapper.listDataToDomain(dishService.getAll()).let {
            save(it)
            return it
        }
    }

    override suspend fun save(dishes: List<Dish>) {
        dishDao.deleteAll()
        for (dish in dishes){
            dishDao.insert(DishEntityMapper.domainToEntity(dish))
        }
    }
}