package com.example.mechat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import com.example.mechat.databinding.MainScreenBinding
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.view.adapter.OurViewPageAdapter
import com.google.android.material.tabs.TabLayout

class WelecomActivity : AppCompatActivity()
{
    private lateinit var binding1 : MainScreenBinding
    private lateinit var adapter: PagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1 = MainScreenBinding.inflate(layoutInflater)
        setContentView(binding1.root)
        val fragmentManager1 = supportFragmentManager
        adapter =  OurViewPageAdapter(fragmentManager1,1)
        binding1.viewPager.adapter=adapter
        binding1.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding1.tabView))
        binding1.tabView.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
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