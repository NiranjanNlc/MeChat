package com.example.mechat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.mechat.databinding.MainScreenBinding
import com.example.mechat.modal.data.Users
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.view.adapter.TabsFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class WelecomActivity : AppCompatActivity()
{
    private lateinit var binding1 : MainScreenBinding
    private lateinit var adapter: TabsFragmentAdapter
    private lateinit var users: Users
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1 = MainScreenBinding.inflate(layoutInflater)
        val view = binding1.root
        setContentView(view)
        val tabLayout = binding1.tabView
        val viewPager = binding1.viewPager
        adapter = TabsFragmentAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = adapter
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab)
            {
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
//        FirebaseUtils.firebaseUser?.let { getUserDetails( it.uid) }!!
      //  Log.i(" tab selected ", FirebaseUtils.firebaseUser?.uid.toString())
    }

//    private fun getUserDetails(uid : String){
//        val valueEventListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
////                users = dataSnapshot.child("$uid").getValue(Users::class.java)!!
////                if (users != null) {
////                    Log.d("TAG users ",users.toString())
////                }
//
//                //Do what you need to do with the value of name
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                Log.d("TAG", databaseError.getMessage()) //Don't ignore errors!
//            }
//        }
//            FirebaseUtils.database.child("users").addListenerForSingleValueEvent(valueEventListener)
//    }

}
