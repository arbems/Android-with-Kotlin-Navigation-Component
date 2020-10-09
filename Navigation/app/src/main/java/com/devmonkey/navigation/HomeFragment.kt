package com.devmonkey.navigation

import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        navController = findNavController()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                Toast.makeText(context, "Clicked favorite!", Toast.LENGTH_SHORT)
                    .show()
                true
            }
            R.id.search -> {
                Toast.makeText(context, "Clicked search!", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.settings -> {
                // Navigation with ID
                navController.navigate(R.id.settingsFragment)
                true
            }
            R.id.about -> {
                // Navigation with URI
                val deepLink = Uri.parse("android-app://androidx.navigation.app/about")
                navController.navigate(deepLink)
                true
            }
            else -> false
        }
    }
}