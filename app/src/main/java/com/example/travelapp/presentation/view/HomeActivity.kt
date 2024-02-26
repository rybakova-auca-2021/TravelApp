package com.example.travelapp.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.travelapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.btn_home -> {
                    navController.navigate(R.id.mainPageFragment2)
                    true
                }

                R.id.btn_search -> {
                    navController.navigate(R.id.searchFragment)
                    true
                }

                R.id.btn_weather -> {
                    navController.navigate(R.id.weatherPageFragment)
                    true
                }

                R.id.btn_saved -> {
                    navController.navigate(R.id.savedPlacesFragment)
                    true
                }

                R.id.btn_profile -> {
                    navController.navigate(R.id.profilePageFragment)
                    true
                }

                else -> false
            }
        }
    }
    fun hideBtmNav() {
        val navBar = findViewById<View>(R.id.bottomNavigationView)
        navBar.visibility = View.GONE
    }

    fun showBtmNav() {
        val navBar = findViewById<View>(R.id.bottomNavigationView)
        navBar.visibility = View.VISIBLE
    }
}