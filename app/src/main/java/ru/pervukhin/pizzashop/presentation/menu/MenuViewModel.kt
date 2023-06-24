package ru.pervukhin.pizzashop.presentation.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ru.pervukhin.pizzashop.data.DishRepositoryImpl
import ru.pervukhin.pizzashop.domain.Dish
import ru.pervukhin.pizzashop.domain.DishRepository
import ru.pervukhin.pizzashop.domain.InternetConnection
import ru.pervukhin.pizzashop.presentation.App
import javax.inject.Inject

class MenuViewModel : ViewModel() {
    val liveData: MutableLiveData<DishState> = MutableLiveData()
    @Inject
    lateinit var dishRepository: DishRepository
    @Inject
    lateinit var internetConnection: InternetConnection

    init {
        App.appComponent.inject(this)
    }

    fun getAll(){
        liveData.value = DishState.Loading
        viewModelScope.launch {
            if (internetConnection.hasInternet()) {
                dishRepository.getAllFromInternet().let {
                    if (it.isNotEmpty()) {
                        liveData.value = DishState.Success(it)
                    } else {
                        liveData.value = DishState.Error
                    }
                }
            }else{
                dishRepository.getAllFromDataBase().let {
                    if (it.isNotEmpty()){
                        liveData.value = DishState.NoInternet(it)
                    }else{
                        liveData.value = DishState.Error
                    }
                }
            }
        }
    }

    sealed class DishState{
        object Loading: DishState()
        data class Success(val dishes: List<Dish>): DishState()
        object Error: DishState()
        data class NoInternet(val dishesDb: List<Dish>): DishState()
    }
}