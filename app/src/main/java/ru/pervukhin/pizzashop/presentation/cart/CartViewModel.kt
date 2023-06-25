package ru.pervukhin.pizzashop.presentation.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pervukhin.pizzashop.domain.CartDish
import ru.pervukhin.pizzashop.domain.CartDishRepository
import ru.pervukhin.pizzashop.presentation.App
import javax.inject.Inject

class CartViewModel : ViewModel() {
    val liveData: MutableLiveData<CartDishState> = MutableLiveData()

    @Inject
    lateinit var cartDishRepository: CartDishRepository

    init {
        App.appComponent.inject(this)
    }

    fun getAll() {
        viewModelScope.launch {
            cartDishRepository.getAll().let {
                setState(it)
            }
        }
    }

    private fun setState(list: List<CartDish>){
        if (list.isEmpty()){
            liveData.value = CartDishState.Empty
        }else{
            liveData.value = CartDishState.Success(list)
        }
    }

    fun plusOne(cartDish: CartDish){
        viewModelScope.launch {
            cartDishRepository.plusOneById(cartDish.id)
            getAll()
        }
    }

    fun minusOne(cartDish: CartDish){
        viewModelScope.launch {
            cartDishRepository.minusOneById(cartDish.id)
            getAll()
        }
    }

    fun remove(cartDish: CartDish){
        viewModelScope.launch {
            cartDishRepository.removeById(cartDish.id)
            liveData.value.let {
                if (it is CartDishState.Success){
                    setState(it.cartDishes.minus(cartDish))
                }
            }
        }
    }

    sealed class CartDishState{
        data class Success(val cartDishes: List<CartDish>): CartDishState()
        object Empty: CartDishState()
    }
}