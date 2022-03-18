package com.example.mechat.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.mechat.R
import com.example.mechat.databinding.ActivityChatDetailBinding
import com.example.mechat.modal.data.ChatMessage
import com.example.mechat.modal.data.Users
import com.example.mechat.utils.Extensions.toast
import com.example.mechat.view.adapter.ChatAdapter
import com.example.mechat.viewmodal.ChatViewModal
import com.example.mechat.viewmodal.ViewModalFactory


class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatDetailBinding
    private lateinit var viewModal: ChatViewModal
    private lateinit var adapter: ChatAdapter
    private lateinit var receiver: Users
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_detail)
        viewModal = ViewModalFactory().create(ChatViewModal::class.java)
        binding.viewmodal = viewModal
        receiver = intent.extras?.get("receiver") as Users
        setSenderReceiver()
        obserVeViewModel()
        viewModal.getLogineduseer()
        viewModal.refreshMessgaeList()
    }

    private fun initializeAdapter(messages: List<ChatMessage>) {
        adapter = ChatAdapter(this, messages)
    }

    private fun obserVeViewModel() {
        viewModal.messageList.observe(this) {
            initializeAdapter(it)
            initializeRecyclerView()
        }
        binding.profileImage.load(receiver.profilePic)
    }

    private fun setSenderReceiver() {
        binding.username.text = receiver.userName
        binding.viewmodal?.recieverId?.value = receiver.userId
        binding.backArrow.setOnClickListener {
            reverseBackToUserList()
        }
        binding.sendMessage.setOnClickListener {
            sendingMessage()
        }
    }

    private fun initializeRecyclerView() {
        binding.chatMessageList.adapter = adapter
        binding.chatMessageList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        divider.setDrawable(
            ContextCompat.getDrawable(baseContext, R.drawable.line_divider)!!
        )
        binding.chatMessageList.addItemDecoration(divider)
    }

    private fun sendingMessage() {
        val messageText = binding.editTextTextMultiLine.text
        if (messageText.isNotBlank() && messageText.isNotEmpty()) {
            viewModal.sendMessage()
        }
        binding.editTextTextMultiLine.text.clear()
    }

    private fun reverseBackToUserList() {
        startActivity(Intent(this, Home::class.java))
        toast("Home Screen .........")
        finish()
    }
}