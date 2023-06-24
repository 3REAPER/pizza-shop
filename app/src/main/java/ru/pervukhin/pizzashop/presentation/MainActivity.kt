package ru.pervukhin.pizzashop.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.pervukhin.pizzashop.R

class MainActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController
    private var selectedItem = R.id.menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val bottomNavigationBar: BottomNavigationView = findViewById(R.id.bottom_navigation_bar)

        bottomNavigationBar.setOnItemSelectedListener {
            if (selectedItem != it.itemId)
            when (it.itemId) {
                R.id.menu -> {
                    selectedItem = it.itemId
                    navigationController.navigate(R.id.menuFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_profile -> {
                    selectedItem = it.itemId
                    navigationController.navigate(R.id.profileFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_cart -> {
                    selectedItem = it.itemId
                    navigationController.navigate(R.id.cartFragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onBackPressed() {
    }
}