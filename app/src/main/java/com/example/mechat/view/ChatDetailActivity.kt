package com.example.mechat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechat.databinding.ActivityChatDetailBinding
import com.example.mechat.modal.data.ChatMessage
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.view.adapter.DetailChatAdapter
import com.example.mechat.viewmodal.ChatDetailViewModal
import com.example.mechat.viewmodal.ViewModalFactory
import androidx.recyclerview.widget.DividerItemDecoration

import androidx.core.content.ContextCompat
import com.example.mechat.R
import com.example.mechat.modal.data.Users


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
        setSenderReceiver(intent.extras?.get("receiver") as Users)
        onserVeViewModel()
    }

    private fun initializeAdapter(messages: List<ChatMessage>) {
        adapter= DetailChatAdapter(this,messages )
    }

    private fun onserVeViewModel() {
        viewModal.messageList.observe(this,{
            initializeAdapter(it)
             initializeRecyclerView()
        })
    }

    private fun setSenderReceiver(user: Any?) {
        var senderId = FirebaseUtils.firebaseUser?.uid
        var recieverId = intent.getStringExtra("userId")
        var userName = intent.getStringExtra("userName")
        Log.i("Send", " $senderId was  $recieverId  thus $userName")
        Log.i("Receiver", " Nlc please ${user}")
        binding.username.text = userName
        binding.viewmodal?.recieverId?.value = recieverId
        binding.viewmodal?.senderId?.value = senderId
        viewModal.setSenderReceiver(senderId, recieverId)
    }

    private fun initializeRecyclerView() {
        binding.chatMessageList.adapter = adapter
        binding.chatMessageList.layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(baseContext, com.example.mechat.R.drawable.line_divider)!!
        )
        binding.chatMessageList.addItemDecoration(divider)

    }

    private fun sendngMessage()
    {
        val messageText = binding.editTextTextMultiLine.text
        if (messageText.isNotBlank() && messageText.isNotEmpty())
            viewModal.sendMessage()
    }
}