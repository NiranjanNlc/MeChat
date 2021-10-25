package com.example.mechat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.PagerAdapter
import com.example.mechat.databinding.MainScreenBinding
import com.example.mechat.view.adapter.TabViewPageAdapter
import com.example.mechat.viewmodal.UserListViewModal
import com.example.mechat.viewmodal.ViewModalFactory
import com.google.android.material.tabs.TabLayout

class WelecomActivity : AppCompatActivity()
{
    private lateinit var binding1 : MainScreenBinding
    private lateinit var adapter: PagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1 = MainScreenBinding.inflate(layoutInflater)
        setContentView(binding1.root)
        val tabLayout = binding1.tabView
        tabLayout.addTab(tabLayout.newTab().setText("Chat "))
        tabLayout.addTab(tabLayout.newTab().setText("UserList "))
        val fragmentManager1 = supportFragmentManager
        adapter =  TabViewPageAdapter(fragmentManager1,binding1.tabView.tabCount)
        binding1.viewPager.adapter=adapter
        binding1.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding1.viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?)
            {

            }

            override fun onTabReselected(tab: TabLayout.Tab?)
            {

            }

        })
    }

}