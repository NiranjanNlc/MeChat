package com.example.mechat.view.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.mechat.R
import com.example.mechat.databinding.MainScreenBinding
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.utils.FirebaseUtils.firebaseAuth
import com.example.mechat.view.adapter.TabsFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class WelecomActivity : AppCompatActivity()
{
    private lateinit var binding1 : MainScreenBinding
    private lateinit var adapter: TabsFragmentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1 = MainScreenBinding.inflate(layoutInflater)
        val view = binding1.root
        setContentView(view)
        val tabLayout = binding1.tabView
        val viewPager = binding1.viewPager
        adapter = TabsFragmentAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.i(" tab selected ", tab.position.toString())
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Log.i(" page selected ", position.toString())
                tabLayout.selectTab(tabLayout.getTabAt(position))
                adapter.createFragment(position)
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.getItemId()) {
            R.id.action_add -> {
                firebaseAuth.signOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
