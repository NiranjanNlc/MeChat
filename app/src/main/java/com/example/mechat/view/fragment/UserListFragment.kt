package com.example.mechat.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechat.R
import com.example.mechat.databinding.FragmentUserListBinding
import com.example.mechat.view.adapter.UserListAdapter
import com.example.mechat.viewmodal.UserListViewModal
import com.example.mechat.viewmodal.ViewModalFactory

class UserListFragment : Fragment() {
   private  lateinit var binding: FragmentUserListBinding
    private lateinit var viewModal: UserListViewModal
    private lateinit var adapter : UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(" in the ", " user list ")
        binding = FragmentUserListBinding.inflate(layoutInflater,container,false)
    //    binding = DataBindingUtil.setContentView(requireActivity(),R.layout.fragment_user_list)
 //       bindData()
//        setUpAdapter()
//        initRecyclerView()
//        observeChange()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseSampleViewModal()
        setUpAdapter()
        initRecyclerView()
    //    observeChange()
    }

    override fun onDestroy() {
        super.onDestroy()
       // viewModal.userList.observe()
     //   lifecycle.removeObserver(viewLifecycleOwner)
    }
    private fun setUpAdapter()
    {
        adapter = UserListAdapter(requireContext())
    }
    private fun initRecyclerView()
    {
        println(" recycler view initiated")
        binding.list.layoutManager = LinearLayoutManager(activity)
        binding.list.setHasFixedSize(true)
        binding.list.adapter=   adapter
        println(viewModal.userList.value)
        adapter.submitList(viewModal.userList.value)
    }

    private fun observeChange()
    {
        viewModal.userList.observe(viewLifecycleOwner, Observer
        {
            println(it)
            Log.i(" vlaues reterived ", it.toString())
               // adapter.submitList(it)
                println(" hello nepoal " ,)
        })
        viewModal.userList.observe(viewLifecycleOwner, {
            print(it)
            Log.i("Valuees,setUp adapter", it.toString())
            Log.i("it vlaues testing ",(it.isEmpty().toString()))
                print(" Niranjan lamichhane nlc is born on nijgadh ")
                adapter.submitList(it)
        })
    }
    private fun initialiseSampleViewModal()
    {
        viewModal = ViewModalFactory().create(UserListViewModal ::class.java)
    }
}