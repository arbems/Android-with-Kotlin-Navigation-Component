package com.devmonkey.viewpager2.blankFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.devmonkey.viewpager2.R
import com.devmonkey.viewpager2.viewpagerFragment.ViewPagerContainerFragmentDirections
import kotlinx.android.synthetic.main.fragment_playlist1.*

class Playlist1Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlist1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            val action = ViewPagerContainerFragmentDirections.actionViewPagerContainerFragmentToPlaylist2Fragment()
            findNavController().navigate(action)
        }
    }
}