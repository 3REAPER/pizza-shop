package ru.pervukhin.pizzashop.data

import ru.pervukhin.pizzashop.data.database.CartDishDao
import ru.pervukhin.pizzashop.data.database.CartDishEntityMapper
import ru.pervukhin.pizzashop.domain.CartDish
import ru.pervukhin.pizzashop.domain.CartDishRepository
import ru.pervukhin.pizzashop.presentation.App
import javax.inject.Inject

class CartDishRepositoryImpl: CartDishRepository {
    @Inject
    lateinit var cartDishDao: CartDishDao

    init {
        App.appComponent.inject(this)
    }

    override suspend fun getAll(): List<CartDish> {
        return CartDishEntityMapper.listEntityToDomain(cartDishDao.getAll())
    }

    override suspend fun add(cartDish: CartDish) {
        cartDishDao.insert(CartDishEntityMapper.domainToEntity(cartDish))
    }

    override suspend fun removeById(id: Int) {
        cartDishDao.deleteById(id)
    }

    override suspend fun plusOneById(id: Int) {
        cartDishDao.plusOne(id)
    }

    override suspend fun minusOneById(id: Int) {
        cartDishDao.minusOne(id)
    }
}