package com.devmonkey.navigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
    }

    /**
     * Menu
     */
    // Setting menu options
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        // Navigates to destination which is both has the same id in menu.xml and nav_graph.xml.
        drawer_layout.closeDrawers()
        item.isChecked = true
        return when (item.itemId) {
            R.id.nav_user -> {
                val action = NavGraphDirections.actionGlobalUserFragment()
                navController.navigate(action)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
                true
            }
        }
    }

    /**
     * Press of the up and back button
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return when (navController.currentDestination?.id) {
            R.id.userFragment -> {
                val action = NavGraphDirections.actionGlobalMainFragment()
                navController.navigate(action)
                true
            }
            else -> navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            val navController = findNavController(R.id.nav_host_fragment)
            when (navController.currentDestination?.id) {
                R.id.userFragment -> {
                    val action = NavGraphDirections.actionGlobalMainFragment()
                    navController.navigate(action)
                }
                else -> super.onBackPressed()
            }
        }
    }


    /**
     * Setup Navigation
     */
    private fun setupNavigation() {

        navController = findNavController(R.id.nav_host_fragment)

        // Set a Toolbar to act as the ActionBar for this Activity window.
        // This is not required for Navigation view, back arrow or navigation icon button, only Needed for displaying menu icons
        setSupportActionBar(toolbar)

        // Configure AppBarConfiguration to manage navigation button behavior
        // This configuration is for top-level destinations, which fragments should display menu button and the ones not included back arrow
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.mainFragment, R.id.settingsFragment), drawer_layout)

        // Configures a NavigationView to use with a NavController
        setupNavigationMenu(navController)

        // Toolbar to set back and menu icon
        toolbar.setupWithNavController(navController, appBarConfiguration)

        listeningNavigationChanges(navController, toolbar)

        // log(navController)
    }

    private fun setupNavigationMenu(navController: NavController) {
        // Use NavigationUI to set up a Navigation View
        // This does not modify the appBar
        nav_view.setupWithNavController(navController)

        // listen item selected
        nav_view.setNavigationItemSelectedListener { item ->
            drawer_layout.closeDrawers()
            item.isChecked = true
            when (item.itemId) {
                R.id.nav_home -> {
                    val action = NavGraphDirections.actionGlobalMainFragment()
                    navController.navigate(action)
                }
                R.id.nav_playlist -> {
                    navController.navigate(R.id.playlistFragment)
                }
                R.id.nav_albums -> {
                    navController.navigate(R.id.albumsFragment)
                }
                R.id.nav_settings -> {
                    navController.navigate(R.id.settingsFragment)
                }
            }

            true
        }
    }

    /**
     * Listening navigation changes
     */
    // This is for listening when a transition happens to a fragment and, change some features
    private fun listeningNavigationChanges(navController: NavController, toolbar: Toolbar) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.userFragment) {
                toolbar.visibility = View.GONE
            } else {
                toolbar.visibility = View.VISIBLE
            }
        }
    }

    /**
     * Log
     */
    private fun log(navController: NavController) {
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
