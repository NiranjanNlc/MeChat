package com.example.mechat.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mechat.view.fragment.ChatFragment
import com.example.mechat.view.fragment.UserFragment

class OurViewPageAdapter(fm : FragmentManager, val behavior: Int) : FragmentPagerAdapter (fm,behavior)
{
    override fun getCount(): Int {
       return behavior
    }

    override fun getItem(position: Int): Fragment
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