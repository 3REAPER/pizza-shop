package ru.pervukhin.pizzashop.presentation.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.pervukhin.pizzashop.R
import ru.pervukhin.pizzashop.domain.CartDish

class CartDishAdapter(private val listener: CountListener): RecyclerView.Adapter<CartDishAdapter.CartDishViewHolder>() {
    private var list: List<CartDish> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartDishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartDishViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartDishViewHolder, position: Int) {
        val name = holder.itemView.findViewById<TextView>(R.id.name)
        val description = holder.itemView.findViewById<TextView>(R.id.description)
        val image = holder.itemView.findViewById<ImageView>(R.id.image)
        val price = holder.itemView.findViewById<TextView>(R.id.price)
        val plus = holder.itemView.findViewById<ImageView>(R.id.plus)
        val minus = holder.itemView.findViewById<ImageView>(R.id.minus)
        val count = holder.itemView.findViewById<TextView>(R.id.count_text)

        val cartDish = list[position]

        name.text = cartDish.name
        description.text = cartDish.description
        count.text = cartDish.count.toString()
        price.text = cartDish.price.toString()

        Glide.with(holder.itemView).load(cartDish.image).into(image)

        plus.setOnClickListener {
            if (cartDish.count != 99) {
                listener.onPlusClick(cartDish)
                count.text = (count.text.toString().toInt() + 1).toString()
            }
        }

        minus.setOnClickListener {
            if (cartDish.count != 1) {
                listener.onMinusClick(cartDish)
                count.text = (count.text.toString().toInt() - 1).toString()
            }else{
                listener.onRemoveItem(cartDish)
                list = list.minus(cartDish)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<CartDish>){
        this.list = list
        notifyDataSetChanged()
    }


    class CartDishViewHolder(view: View): RecyclerView.ViewHolder(view)
}