package com.example.mechat.view.adapter

import android.util.Log
import androidx.fragment.app.*
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mechat.view.fragment.ChatListFragment
import com.example.mechat.view.fragment.UserListFragment

class TabsFragmentAdapter(val fm: FragmentManager, val lifecycle: Lifecycle) :
    FragmentStateAdapter(fm,lifecycle)
{
    override fun getItemCount(): Int {
        return NUM_TABS
    }
    override fun createFragment(position: Int): Fragment
    {
        Log.i(" poition ", " $position")
        if(position ===0 )
            return ChatListFragment()
        return UserListFragment()

    }

    companion object {
        const  val  NUM_TABS = 2
    }
}