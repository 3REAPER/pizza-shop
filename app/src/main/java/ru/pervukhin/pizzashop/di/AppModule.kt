package ru.pervukhin.pizzashop.di

import dagger.Module
import dagger.Provides
import ru.pervukhin.pizzashop.data.DishRepositoryImpl
import ru.pervukhin.pizzashop.data.internet.InternetConnectionImpl
import ru.pervukhin.pizzashop.domain.DishRepository
import ru.pervukhin.pizzashop.domain.InternetConnection
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDishRepository(): DishRepository{
        return DishRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideInternetConnection(): InternetConnection{
        return InternetConnectionImpl()
    }
}