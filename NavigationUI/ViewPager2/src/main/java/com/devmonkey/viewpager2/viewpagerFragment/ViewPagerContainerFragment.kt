package com.devmonkey.viewpager2.viewpagerFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devmonkey.viewpager2.R
import com.devmonkey.viewpager2.adapter.ChildFragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_view_pager_container.*

class ViewPagerContainerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_pager_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set Adapter for ViewPager inside this fragment using this Fragment, more specifically childFragmentManager as param
        view_pager.adapter =
            ChildFragmentStateAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)

        // Bind tabs and viewpager
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = when (position) {
                0 -> "Playlist"
                1 -> "Álbumes"
                2 -> "Artistas"
                3 -> "Podcast"
                4 -> "Escuchado recientemente"
                else -> "Home"
            }
        }.attach()
    }
}