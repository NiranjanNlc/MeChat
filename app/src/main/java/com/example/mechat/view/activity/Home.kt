package com.example.mechat.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.mechat.R
import com.example.mechat.databinding.MainScreenBinding
import com.example.mechat.utils.Extensions.toast
import com.example.mechat.utils.FirebaseUtils.firebaseAuth
import com.example.mechat.view.adapter.TabsFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.firebase.auth.FirebaseUser


class Home : AppCompatActivity()
{
    private lateinit var binding1 : MainScreenBinding
    private lateinit var adapter: TabsFragmentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1 = MainScreenBinding.inflate(layoutInflater)
        val view = binding1.root
        setContentView(view)
        setSupportActionBar(binding1.myToolbar)
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
        return when (item.itemId) {
            R.id.action_add -> {
                firebaseAuth.signOut()
                checkUser()
                true
            }
            R.id.setting-> {
                openUserSetting()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openUserSetting() {
        startActivity(Intent(this, Setting::class.java))
        toast("Setting opened  ")
        finish()
    }

    override fun onStart() {
        super.onStart()
        checkUser()
    }

    private fun checkUser() {
        val user: FirebaseUser? = firebaseAuth.currentUser
        if (user == null) {
            startActivity(Intent(this, Login::class.java))
            toast("Logged out sucess fully ")
            finish()
        }
    }
}
