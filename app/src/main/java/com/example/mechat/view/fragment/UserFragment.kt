package com.example.mechat.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.mechat.R
import com.example.mechat.databinding.FragmentUserListBinding
import com.example.mechat.viewmodal.AuthenciationViewModal
import com.example.mechat.viewmodal.UserListViewModal
import com.example.mechat.viewmodal.ViewModalFactory
import com.firebase.ui.auth.data.model.User

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    private lateinit var viewModal: UserListViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModal = ViewModalFactory().create(UserListViewModal ::class.java)
        setUpAdapter()
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
            Log.i(" vlaues reterived ", it.toString())
        })
    }

}