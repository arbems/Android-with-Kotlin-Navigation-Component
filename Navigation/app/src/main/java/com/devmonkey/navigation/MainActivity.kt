package com.devmonkey.navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigationController()

        log()
    }

    // This method is called whenever the user chooses to navigate Up within your application's activity hierarchy from the action bar.
    override fun onSupportNavigateUp(): Boolean {
        return when (navController.currentDestination?.id) {
            R.id.galleryFragment -> {
                val action = NavGraphDirections.actionGlobalHomeFragment()
                navController.navigate(action)
                true
            }
            R.id.slideshowFragment -> {
                val action = NavGraphDirections.actionGlobalHomeFragment()
                navController.navigate(action)
                true
            }
            R.id.toolsFragment -> {
                val action = NavGraphDirections.actionGlobalHomeFragment()
                navController.navigate(action)
                true
            }
            R.id.profileFragment -> {
                val action = NavGraphDirections.actionGlobalHomeFragment()
                navController.navigate(action)
                true
            }
            R.id.loginFragment -> {
                val action = NavGraphDirections.actionGlobalHomeFragment()
                navController.navigate(action)
                true
            }
            else -> navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        }
    }

    // Called when the activity has detected the user's press of the back key.
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            when (navController.currentDestination?.id) {
                R.id.galleryFragment -> {
                    val action = NavGraphDirections.actionGlobalHomeFragment()
                    navController.navigate(action)
                }
                R.id.slideshowFragment -> {
                    val action = NavGraphDirections.actionGlobalHomeFragment()
                    navController.navigate(action)
                }
                R.id.toolsFragment -> {
                    val action = NavGraphDirections.actionGlobalHomeFragment()
                    navController.navigate(action)
                }
                R.id.profileFragment -> {
                    val action = NavGraphDirections.actionGlobalHomeFragment()
                    navController.navigate(action)
                }
                R.id.loginFragment -> {
                    val action = NavGraphDirections.actionGlobalHomeFragment()
                    navController.navigate(action)
                }
                else -> super.onBackPressed()
            }
        }
    }

    private fun setupNavigationController() {
        navController = findNavController(R.id.nav_host_fragment)

        setSupportActionBar(toolbar)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawer_layout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navigation_view.setupWithNavController(navController)
        navigation_view.setNavigationItemSelectedListener { menuItem ->
            drawer_layout.closeDrawers()
            menuItem.isChecked = true
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val action = NavGraphDirections.actionGlobalHomeFragment()
                    navController.navigate(action)
                }
                R.id.nav_gallery -> {
                    val action = HomeFragmentDirections.actionHomeFragmentToGalleryFragment()
                    navController.navigate(action)
                }
                R.id.nav_slideshow -> {
                    val action = HomeFragmentDirections.actionHomeFragmentToSlideshowFragment()
                    navController.navigate(action)
                }
                R.id.nav_tools -> {
                    val action = HomeFragmentDirections.actionHomeFragmentToToolsFragment()
                    navController.navigate(action)
                }
                R.id.nav_profile -> {
                    val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment()
                    navController.navigate(action)
                }
            }

            true
        }
    }

    private fun log() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Toast.makeText(
                this@MainActivity,
                "Navigated to ${destination.label}",
                Toast.LENGTH_SHORT
            ).show()
            Log.d("NavigationActivity", "Navigated to ${destination.label}")
        }
    }

}