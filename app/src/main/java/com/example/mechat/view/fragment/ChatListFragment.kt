package com.example.mechat.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechat.databinding.FragmentChatBinding
import com.example.mechat.modal.data.Chats
import com.example.mechat.view.activity.ChatActivity
import com.example.mechat.view.adapter.ChatListAdapter
import com.example.mechat.viewmodal.HomeViewModal
import com.example.mechat.viewmodal.ViewModalFactory

class ChatListFragment : Fragment(),ChatListAdapter.OnChatClickListener
{
    private lateinit var binding : FragmentChatBinding
    private lateinit var viewModal:HomeViewModal
    private lateinit var adapter:ChatListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        Log.i(" in the ", " chat list ")
        binding = FragmentChatBinding.inflate(layoutInflater,container,false)
        initialiseSampleViewModal()
        Log.i(" ddd1" , "${viewModal.chatList.value} ")
        observeChange()
        Log.i(" in the ", " chat list fragments ,,,,,,,,,")
        return  binding.root
    }

    private fun setUpAdapter()
    {
        try {
            adapter = ChatListAdapter(this)
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
        println(" usrr list " + viewModal.chatList.value)
    }

    private fun observeChange()
    {
        viewModal.chatList.observe(viewLifecycleOwner) {
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
        // loginedchats = viewModal.loginedchat.value!!
    }

    override fun onClick(chats: Chats) {
        val i = Intent(activity, ChatActivity ::class.java)
        println(" Nlc chat here $chats.toString()")
//        i.putExtra("chatId",chat.chatId)
//        i.putExtra("chatName",chat.chatName)
//        i.putExtra("chatProfile",chat.chatName)
//        i.putExtra("receiver",chat)
//        //   i.putExtra("loginchat",loginedchats)
        startActivity(i,null)
    }
}


