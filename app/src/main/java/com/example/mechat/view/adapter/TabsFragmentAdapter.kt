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
     val  NUM_TABS = 3
    override fun getItemCount(): Int {
       return NUM_TABS
    }
    override fun createFragment(position: Int): Fragment
    {

        Log.i(" poition ", " $position")
        when (position) {
            1 -> {
                //  val homeFragment: HomeFragment = HomeFragment()

                Log.i(" poition ", " chatt ")
                return ChatListFragment()
            }

            2 ->
            {
                val  descriptionFragment = UserListFragment();

                Log.i(" poition ", " userlist  ..........")
//                 val  transaction =fm.beginTransaction();
//                transaction.add(R.id.userList, descriptionFragment ).commit()
                return descriptionFragment
            }
            else ->
            {
                return ChatListFragment()
            }
        }
    }
}