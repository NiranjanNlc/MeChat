package com.example.mechat.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechat.databinding.FragmentUserListBinding
import com.example.mechat.modal.data.Users
import com.example.mechat.view.activity.ChatActivity
import com.example.mechat.view.adapter.UserListAdapter
import com.example.mechat.viewmodal.HomeViewModal
import com.example.mechat.viewmodal.ViewModalFactory

class UserListFragment : Fragment() ,UserListAdapter.OnUserClickListener{
    private  lateinit var binding: FragmentUserListBinding
    private lateinit var viewModal: HomeViewModal
    private lateinit var adapter : UserListAdapter
    private lateinit var loginedUsers: Users

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(" in the ", " user list ")
        binding = FragmentUserListBinding.inflate(layoutInflater,container,false)
        initialiseSampleViewModal()
        Log.i(" ddd1" , "${viewModal.userList.value} ")
        observeChange()
        return binding.root
    }
    private fun setUpAdapter()
    {
        try {
            adapter = UserListAdapter( this)
        }
        catch (e:Exception)
        {
            Log.i( " error for adapeter ", e.message.toString())
        }
    }
    private fun initRecyclerView() {
        println(" recycler view initiated")
        binding.list.layoutManager = LinearLayoutManager(activity)
        binding.list.setHasFixedSize(true)
        binding.list.adapter = adapter
        println(" usrr list " + viewModal.userList.value)
    }

    private fun observeChange()
    {
        viewModal.userList.observe(viewLifecycleOwner) {
            setUpAdapter()
            initRecyclerView()
            println(it)
            Log.i(" vlaues reterived ", it.toString())
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
            println(" hello nepoal ")
        }

    }
    private fun initialiseSampleViewModal()
    {
        viewModal = ViewModalFactory().create(HomeViewModal ::class.java)
        viewModal.getLogineduseer()
       // loginedUsers = viewModal.loginedUser.value!!
    }


    override fun onClick(user: Users) {
        val i = Intent(activity, ChatActivity ::class.java)
        println(" Nlc user here $user.toString()")
        i.putExtra("userId",user.userId)
        i.putExtra("userName",user.userName)
        i.putExtra("userProfile",user.userName)
        i.putExtra("receiver",user)
     //   i.putExtra("loginUser",loginedUsers)
        startActivity(i,null)
    }

}