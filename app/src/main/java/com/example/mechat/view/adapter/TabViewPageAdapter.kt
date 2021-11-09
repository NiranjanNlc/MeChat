package com.example.mechat.view.adapter

import androidx.fragment.app.*
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mechat.R
import com.example.mechat.view.fragment.ChatFragment
import com.example.mechat.view.fragment.UserFragment

class TabViewPageAdapter(val fm: FragmentManager, val lifecycle: Lifecycle) :
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

            else ->
            {
                val  descriptionFragment = UserFragment();
//                 val  transaction =fm.beginTransaction();
//                transaction.add(R.id.userList, descriptionFragment ).commit()
                return descriptionFragment
            }
        }
    }
}