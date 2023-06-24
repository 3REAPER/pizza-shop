package ru.pervukhin.pizzashop.presentation.menu

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.pervukhin.pizzashop.R
import ru.pervukhin.pizzashop.domain.Dish
import kotlin.coroutines.coroutineContext

class DishAdapter(): RecyclerView.Adapter<DishAdapter.DishViewHolder>() {
    private var list: List<Dish> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dish, parent, false)
        return DishViewHolder(view)
    }
    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val image = holder.itemView.findViewById<ImageView>(R.id.image)
        val title = holder.itemView.findViewById<TextView>(R.id.title)
        val description = holder.itemView.findViewById<TextView>(R.id.description)
        val price = holder.itemView.findViewById<TextView>(R.id.price)

        val dish = list[position]

        title.text = dish.name
        title.tag = dish.category
        description.text = dish.description
        price.text = "от ${dish.price} р"
        Glide.with(holder.itemView).load(dish.image).into(image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getPositionElementByCategory(category: String): Int{
        for (i in list.indices){
            if (list[i].category == category){
                return i
            }
        }
        return -1
    }

    fun setList(list: List<Dish>){
        this.list = list
        notifyDataSetChanged()
    }

    class DishViewHolder(view: View): RecyclerView.ViewHolder(view)
}