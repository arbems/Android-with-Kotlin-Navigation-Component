package com.arbems.navigationcomponent

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_a.*

class AFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button1.setOnClickListener {
            val action = AFragmentDirections.actionAFragmentToBFragment()
            view.findNavController().navigate(action)
        }

        button2.setOnClickListener {
            // navigate with ID
            view.findNavController().navigate(R.id.CFragment)
            // using createNavigateOnClickListener()
            // Navigation.createNavigateOnClickListener(R.id.CFragment, null)
        }

        btnNestedGraph.setOnClickListener {
            val action = AFragmentDirections.actionAFragmentToDFragment()
            view.findNavController().navigate(action)
        }

        btnIncludeGraph.setOnClickListener {
            val action = AFragmentDirections.actionAFragmentToIncludedGraph()
            view.findNavController().navigate(action)
        }

        buttonURI.setOnClickListener {
            val navController = findNavController()
            val deepLink = Uri.parse("android-app://androidx.navigation.app/profile")
            findNavController().navigate(deepLink)
        }
    }
}