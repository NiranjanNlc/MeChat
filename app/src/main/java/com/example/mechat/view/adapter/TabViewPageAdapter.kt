package com.example.mechat.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mechat.view.fragment.ChatFragment
import com.example.mechat.view.fragment.UserFragment

class TabViewPageAdapter(fm: FragmentManager, val lifecycle: Lifecycle) :
    FragmentStateAdapter(fm,lifecycle)
{
     val  NUM_TABS = 3
    override fun getItemCount(): Int {
       return NUM_TABS
    }
    override fun createFragment(position: Int): Fragment
    {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return ChatFragment()
            }

            else -> return UserFragment()
        }
    }
}