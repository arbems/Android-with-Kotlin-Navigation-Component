package com.arbems.passdatabetweendestinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_c.*
import kotlinx.android.synthetic.main.fragment_c.buttonBack

class CFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_c, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonBack.setOnClickListener {
            // Pass data to the start destination: create bundle and pass it
            var args = bundleOf("price" to 29)
            view.findNavController().setGraph(R.navigation.nav_graph, args)
        }

        // Pass data between destinations with Bundle objects: retrieve the Bundle
        val amount = arguments?.getString("amount")
        textViewArg.text = "Param amount: $amount"
    }
}