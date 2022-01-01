package com.example.mechat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechat.R
import com.example.mechat.databinding.ActivityChatDetailBinding
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.view.adapter.DetailChatAdapter
import com.example.mechat.viewmodal.ChatDetailViewModal
import com.example.mechat.viewmodal.ViewModalFactory

class ChatDetailActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityChatDetailBinding
    private lateinit var viewModal: ChatDetailViewModal
    private lateinit var adapter: DetailChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_detail)
        viewModal  = ViewModalFactory().create(ChatDetailViewModal ::class.java)
        binding.viewmodal = viewModal
        setSenderReceiver()
        initializeRecyclerView()
        onserVeViewModel()
    }

    private fun onserVeViewModel() {
        viewModal.messageList.observe(this,{
            adapter = DetailChatAdapter(this,it)
            binding.chatMessageList.adapter = adapter
        })
    }

    private fun setSenderReceiver() {
        var senderId = FirebaseUtils.firebaseUser?.uid
        var recieverId = intent.getStringExtra("userId")
        var userName = intent.getStringExtra("userName")
        i(" intent ", " $senderId was  $recieverId  thus $userName")
        binding.username.text = userName
        binding.viewmodal.recieverId.value = recieverId
        binding.viewmodal.senderId.value = senderId
        viewModal.setSenderReceiver(senderId, recieverId)
    }

    private fun initializeRecyclerView() {
        binding.chatMessageList.adapter = adapter
        binding.chatMessageList.layoutManager = LinearLayoutManager(this)
    }

    private fun sendngMessage()
    {
        val messageText = binding.editTextTextMultiLine.text
        if (messageText.isNotBlank() && messageText.isNotEmpty())
            viewModal.sendMessage()
    }
}