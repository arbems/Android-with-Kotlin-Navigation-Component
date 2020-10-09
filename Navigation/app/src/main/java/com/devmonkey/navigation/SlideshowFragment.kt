package com.devmonkey.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_slideshow.*

class SlideshowFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_slideshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            val slideshow = 1
            val action = SlideshowFragmentDirections.actionSlideshowFragmentToSlideshowDetailFragment(slideshow)
            view.findNavController().navigate(action)
        }

        button2.setOnClickListener {
            val slideshow = 2
            val action = SlideshowFragmentDirections.actionSlideshowFragmentToSlideshowDetailFragment(slideshow)
            view.findNavController().navigate(action)
        }

        button3.setOnClickListener {
            val action = SlideshowFragmentDirections.actionSlideshowFragmentToSlideshowDetailFragment()
            view.findNavController().navigate(action)
        }
    }
}