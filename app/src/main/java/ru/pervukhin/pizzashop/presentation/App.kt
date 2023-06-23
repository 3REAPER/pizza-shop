package ru.pervukhin.pizzashop.presentation

import android.app.Application
import ru.pervukhin.pizzashop.di.AppComponent
import ru.pervukhin.pizzashop.di.DaggerAppComponent
import ru.pervukhin.pizzashop.di.RoomModule

class App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .roomModule(RoomModule(this))
            .build()
    }
}