package com.arbems.passdatabetweendestinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_b.*

class BFragment : Fragment() {

    // Safe Args to pass data: ktx dependencies by navArgs() property delegate to access arguments
    private val args: BFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonGoToC.setOnClickListener {
            // Pass data between destinations with Bundle objects: create a Bundle object and pass it
            val amount = "12"
            var bundle = bundleOf("amount" to amount)
            view.findNavController().navigate(R.id.CFragment, bundle)

        }

        buttonBack.setOnClickListener {
            // Use Safe Args to navigate a global action
            val action = NavGraphDirections.actionGlobalAFragment()
            view.findNavController().navigate(action)
        }

        // Safe Args to pass data: access the arguments
        var arg = args?.myArg?.toString()
        textViewArg.text = "Param: $arg"
    }
}