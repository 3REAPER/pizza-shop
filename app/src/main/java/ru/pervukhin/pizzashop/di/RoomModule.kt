package ru.pervukhin.pizzashop.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.pervukhin.pizzashop.data.database.AppDataBase
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
    fun provideAppDataBase(): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java,"dataBase.db").build()
    }
}