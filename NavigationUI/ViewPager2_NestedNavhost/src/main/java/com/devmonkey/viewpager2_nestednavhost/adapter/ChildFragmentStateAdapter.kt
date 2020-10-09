package com.devmonkey.viewpager2_nestednavhost.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.devmonkey.viewpager2_nestednavhost.blankFragments.*
import com.devmonkey.viewpager2_nestednavhost.viewpagerFragment.ViewPagerContainerFragment

class ChildFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Playlist1Fragment()
            1 -> AlbumFragment()
            2 -> ArtistFragment()
            3 -> PodcastFragment()
            4 -> RecentFragment()
            else -> ViewPagerContainerFragment()
        }
    }

}