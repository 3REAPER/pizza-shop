package ru.pervukhin.pizzashop.presentation.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import ru.pervukhin.pizzashop.R
import ru.pervukhin.pizzashop.domain.CartDish

class CartFragment : Fragment(), CountListener {
    private lateinit var viewModel: CartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val empty = view.findViewById<LinearLayout>(R.id.empty)
        val buy = view.findViewById<Button>(R.id.buy)
        val adapter = CartDishAdapter(this)
        recyclerView.adapter = adapter

        viewModel.getAll()

        viewModel.liveData.observe(viewLifecycleOwner){
            when(it){
                CartViewModel.CartDishState.Empty-> {
                    empty.visibility = View.VISIBLE
                    buy.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }
                is CartViewModel.CartDishState.Success ->{
                    adapter.setList(it.cartDishes)
                    buy.text = String.format(resources.getString(R.string.buy), getPrice(it.cartDishes))
                    empty.visibility = View.GONE
                }
            }
        }

        return view
    }

    private fun getPrice(list: List<CartDish>): String{
        var price = 0
        for (cartDish in list){
            price += cartDish.price * cartDish.count
        }
        return if (price == 0){
            ""
        }else{
            price.toString()
        }
    }

    override fun onPlusClick(cartDish: CartDish) {
        viewModel.plusOne(cartDish)
    }

    override fun onMinusClick(cartDish: CartDish) {
        viewModel.minusOne(cartDish)
    }

    override fun onRemoveItem(cartDish: CartDish) {
        viewModel.remove(cartDish)
    }
}