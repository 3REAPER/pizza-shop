package ru.pervukhin.pizzashop.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import ru.pervukhin.pizzashop.data.database.AppDataBase
import ru.pervukhin.pizzashop.data.database.CartDishDao
import ru.pervukhin.pizzashop.data.database.DishDao
import javax.inject.Singleton


@Module
class RoomModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideDishDao(appDataBase: AppDataBase): DishDao {
        return appDataBase.getDishDao()
    }

    @Singleton
    @Provides
    fun provideCartDishDao(appDataBase: AppDataBase): CartDishDao {
        return appDataBase.getCartDishDao()
    }

    @Singleton
    @Provides
    fun provideAppDataBase(): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java,"db.db").build()
    }
}