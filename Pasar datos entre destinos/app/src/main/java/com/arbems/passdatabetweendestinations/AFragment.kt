package com.arbems.passdatabetweendestinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_a.*

class AFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonGoToB.setOnClickListener {
            // Safe Args to pass data: Set up an argument and pass it to the method navigate()
            val myArg = 30
            val action = AFragmentDirections.actionAFragmentToBFragment(myArg)
            view.findNavController().navigate(action)
        }

        // Pass data to the start destination: retrieve a data
        val price = arguments?.get("price")
        textViewArg.text = "Param price: $price"
    }
}