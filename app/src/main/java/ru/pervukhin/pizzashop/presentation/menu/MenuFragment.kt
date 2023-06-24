package ru.pervukhin.pizzashop.presentation.menu

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.pervukhin.pizzashop.R
import ru.pervukhin.pizzashop.domain.Dish
import java.util.*

class MenuFragment : Fragment() {
    private lateinit var viewModel: MenuViewModel
    private lateinit var scrollView: HorizontalScrollView
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryPizza: TextView
    private lateinit var categoryCombo: TextView
    private lateinit var categoryDessert: TextView
    private lateinit var categoryBeverages: TextView
    private lateinit var adapter: DishAdapter
    private lateinit var layoutManager: LinearLayoutManagerWithSmoothScroller
    private var isScroll = false
    private var selectedCategory = Dish.CATEGORY_PIZZA

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        recyclerView = view.findViewById(R.id.recycler_view)
        categoryPizza = view.findViewById(R.id.category_pizza)
        categoryCombo = view.findViewById(R.id.category_combo)
        categoryDessert = view.findViewById(R.id.category_dessert)
        categoryBeverages = view.findViewById(R.id.category_beverages)
        scrollView = view.findViewById(R.id.horizontal_scroll_view)
        val condition = view.findViewById<TextView>(R.id.condition)
        val loading = view.findViewById<FrameLayout>(R.id.loading)

        layoutManager = LinearLayoutManagerWithSmoothScroller(requireContext())
        recyclerView.layoutManager = layoutManager

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val categoryNow = recyclerView.get(0).findViewById<TextView>(R.id.title).tag
                if (!isScroll && selectedCategory != categoryNow) {
                    when (categoryNow) {
                        Dish.CATEGORY_PIZZA -> {
                            selectCategory(categoryPizza, categoryCombo, categoryDessert, categoryBeverages)
                            selectedCategory = Dish.CATEGORY_PIZZA
                        }
                        Dish.CATEGORY_COMBO -> {
                            selectCategory(categoryCombo, categoryPizza, categoryDessert, categoryBeverages)
                            selectedCategory = Dish.CATEGORY_COMBO
                        }
                        Dish.CATEGORY_DESSERT -> {
                            selectCategory(categoryDessert, categoryCombo, categoryPizza, categoryBeverages)
                            selectedCategory = Dish.CATEGORY_DESSERT
                        }
                        Dish.CATEGORY_BEVERAGES -> {
                            selectCategory(categoryBeverages, categoryCombo, categoryDessert, categoryPizza)
                            selectedCategory = Dish.CATEGORY_BEVERAGES
                        }
                    }
                }
            }
        })

        adapter = DishAdapter()
        recyclerView.adapter = adapter

        viewModel.getAll()

        viewModel.liveData.observe(viewLifecycleOwner){
            when(it){
                is MenuViewModel.DishState.Loading ->{
                    loading.visibility = View.VISIBLE
                }
                is MenuViewModel.DishState.Error ->{
                    loading.visibility = View.GONE
                    condition.visibility = View.VISIBLE
                    categoryPizza.isEnabled = false
                    categoryCombo.isEnabled = false
                    categoryDessert.isEnabled = false
                    categoryBeverages.isEnabled = false
                }
                is MenuViewModel.DishState.Success ->{
                    loading.visibility = View.GONE
                    adapter.setList(it.dishes)
                }
                is MenuViewModel.DishState.NoInternet ->{
                    loading.visibility = View.GONE
                    adapter.setList(it.dishesDb)
                }
            }
        }

        categoryPizza.setOnClickListener {
            if (!isScroll) {
                scrollTo(Dish.CATEGORY_PIZZA)
                selectCategory(categoryPizza, categoryCombo, categoryDessert, categoryBeverages)
            }
        }


        categoryCombo.setOnClickListener {
            if (!isScroll) {
                scrollTo(Dish.CATEGORY_COMBO)
                selectCategory(categoryCombo, categoryPizza, categoryDessert, categoryBeverages)
            }
        }


        categoryDessert.setOnClickListener {
            if (!isScroll) {
                scrollTo(Dish.CATEGORY_DESSERT)
                selectCategory(categoryDessert, categoryCombo, categoryPizza, categoryBeverages)
            }
        }

        categoryBeverages.setOnClickListener {
            if (!isScroll) {
                scrollTo(Dish.CATEGORY_BEVERAGES)
                selectCategory(categoryBeverages, categoryCombo, categoryDessert, categoryPizza)
            }
        }
        return view
    }

    private fun scrollTo(category: String){
        isScroll = true
        recyclerView.smoothScrollToPosition(adapter.getPositionElementByCategory(category))
        Timer().schedule(object : TimerTask(){
            override fun run() {
                isScroll = false
            }
        }, 700L)
    }

    private fun selectCategory(selectedCategory: TextView, vararg tags: TextView) {
        if (selectedCategory.id == R.id.category_pizza){
            scrollView.smoothScrollTo(0,0)
        }else {
            scrollView.smoothScrollTo(selectedCategory.x.toInt(), 0)
        }
        selectedCategory.setBackgroundResource(R.drawable.background_category_enabled)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedCategory.typeface = resources.getFont(R.font.sf_ui_display_bold)
        }
        selectedCategory.setTextColor(ResourcesCompat.getColor(resources, R.color.pink, context?.theme))
        for (tag in tags){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                tag.setTypeface(resources.getFont(R.font.sf_ui_display))
            }
            tag.setBackgroundResource(R.drawable.background_category_disabled)
            tag.setTextColor(ResourcesCompat.getColor(resources, R.color.text_category_disabled, context?.theme))
        }
    }
}