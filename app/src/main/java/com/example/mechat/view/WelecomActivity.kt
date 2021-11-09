package com.example.mechat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mechat.databinding.MainScreenBinding
import com.example.mechat.view.adapter.TabViewPageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class WelecomActivity : AppCompatActivity()
{
    private lateinit var binding1 : MainScreenBinding
    private lateinit var adapter: TabViewPageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1 = MainScreenBinding.inflate(layoutInflater)
        val view = binding1.root
        setContentView(view)
        val tabLayout = binding1.tabView
        val viewPager = binding1.viewPager
        adapter =  TabViewPageAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter= adapter
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null)
                {
                    Log.i(" clicked ", " ${tab.text}")
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