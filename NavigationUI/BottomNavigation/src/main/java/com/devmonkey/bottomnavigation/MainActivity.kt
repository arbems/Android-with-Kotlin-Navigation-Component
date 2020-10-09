package com.devmonkey.bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupButtonNavigation()
    }

    private fun setupButtonNavigation() {

        val navController = findNavController(R.id.nav_host_fragment)

        bottom_nav_view.setupWithNavController(navController)

        bottom_nav_view.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.mainFragment)
                    true
                }
                R.id.nav_music -> {
                    navController.navigate(R.id.musicFragment)
                    true
                }
                R.id.nav_favorites -> {
                    navController.navigate(R.id.favoritesFragment)
                    true
                }
                R.id.nav_places -> {
                    navController.navigate(R.id.placesFragment)
                    true
                }
                R.id.nav_news -> {
                    navController.navigate(R.id.newsFragment)
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

}
