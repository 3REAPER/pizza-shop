package ru.pervukhin.pizzashop.di

import dagger.Component
import ru.pervukhin.pizzashop.data.database.DishDao
import ru.pervukhin.pizzashop.data.DishRepositoryImpl
import ru.pervukhin.pizzashop.data.database.CartDishDao
import ru.pervukhin.pizzashop.data.CartDishRepositoryImpl
import ru.pervukhin.pizzashop.data.internet.DishService
import ru.pervukhin.pizzashop.domain.CartDishRepository
import ru.pervukhin.pizzashop.domain.DishRepository
import ru.pervukhin.pizzashop.domain.InternetConnection
import ru.pervukhin.pizzashop.presentation.cart.CartViewModel
import ru.pervukhin.pizzashop.presentation.menu.MenuViewModel
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class, RoomModule::class, AppModule::class])
@Singleton
interface AppComponent {

    fun inject(dishRepositoryImpl: DishRepositoryImpl)
    fun inject(menuViewModel: MenuViewModel)
    fun inject(cartDishRepositoryImpl: CartDishRepositoryImpl)
    fun inject(cartCartViewModel: CartViewModel)

    fun dishService(): DishService

    fun dishDao(): DishDao
    fun cartDishDao(): CartDishDao

    fun dishRepository(): DishRepository
    fun internetConnection(): InternetConnection
    fun cartDishRepository(): CartDishRepository
}