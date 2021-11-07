package com.example.mechat.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechat.R
import com.example.mechat.databinding.FragmentUserListBinding
import com.example.mechat.view.adapter.UserListAdapter
import com.example.mechat.viewmodal.AuthenciationViewModal
import com.example.mechat.viewmodal.UserListViewModal
import com.example.mechat.viewmodal.ViewModalFactory
import com.firebase.ui.auth.data.model.User

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    private lateinit var viewModal: UserListViewModal
    private lateinit var adapter : UserListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserListBinding.inflate(layoutInflater)
        viewModal = ViewModalFactory().create(UserListViewModal ::class.java)
        bindData()
        setUpAdapter()
        initRecyclerView()
        observeChange()
    }

    override fun onDestroy() {
        super.onDestroy()
       // viewModal.userList.observe()
     //   lifecycle.removeObserver(viewLifecycleOwner)
    }
    private fun setUpAdapter()
    {
        viewModal.userList.observe(viewLifecycleOwner, {
            print(it)
            Log.i("Valuees,setUp adapter", it.toString())
            Log.i("it vlaues testing ",(it.isEmpty().toString()))
            if(!(it.isEmpty())) {
                print(" Niranjan lamichhane nlc is born on nijgadh ")
            }
        })

    }
    private fun initRecyclerView()
    {
        println(" recycler view initiated")
        binding.list.layoutManager = LinearLayoutManager(activity)
        binding.list.setHasFixedSize(true)
        binding.list.adapter=   adapter
        adapter.submitList(viewModal.userList.value)
    }

    private fun observeChange()
    {
        viewModal.userList.observe(viewLifecycleOwner, {
            println(it)
            Log.i(" vlaues reterived ", it.toString())
                adapter.submitList(it)
                println(" hello nepoal " ,)
        })
    }

    private fun bindData()
    {
       adapter = UserListAdapter(requireContext())
        // already binf by the view and data binding
    }

    private fun initialiseSampleViewModal() {} //\* sample viewmodal initialised above only */

}