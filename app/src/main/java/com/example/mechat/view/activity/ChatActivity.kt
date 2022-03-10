package com.example.mechat.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechat.databinding.ActivityChatDetailBinding
import com.example.mechat.modal.data.ChatMessage
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.view.adapter.DetailChatAdapter
import com.example.mechat.viewmodal.ChatViewModal
import com.example.mechat.viewmodal.ViewModalFactory
import androidx.recyclerview.widget.DividerItemDecoration

import androidx.core.content.ContextCompat
import com.example.mechat.R
import com.example.mechat.modal.data.Users


class ChatActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityChatDetailBinding
    private lateinit var viewModal: ChatViewModal
    private lateinit var adapter: DetailChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_detail)
        viewModal  = ViewModalFactory().create(ChatViewModal ::class.java)
        binding.viewmodal = viewModal
        setSenderReceiver(intent.extras?.get("receiver") as Users)
        onserVeViewModel()
//        viewModal.refreshMessgaeList()
    }

    private fun initializeAdapter(messages: List<ChatMessage>) {
        adapter= DetailChatAdapter(this,messages )
    }

    private fun onserVeViewModel() {
        viewModal.messageList.observe(this) {
            setSenderReceiver(intent.extras?.get("receiver") as Users)
            initializeAdapter(it)
            initializeRecyclerView()
            viewModal.refreshMessgaeList()
        }
    }

    private fun setSenderReceiver(user: Any?) {
        val senderId = FirebaseUtils.firebaseUser?.uid
        val recieverId = intent.getStringExtra("userId")
        val userName = intent.getStringExtra("userName")
        Log.i("Send", " $senderId was  $recieverId  thus $userName")
        Log.i("Receiver", " Nlc please $user")
        binding.username.text = userName
        binding.viewmodal?.recieverId?.value = recieverId
        binding.viewmodal?.senderId?.value = senderId
        viewModal.setSenderReceiver(senderId, recieverId)
    }

    private fun initializeRecyclerView() {
        binding.chatMessageList.adapter = adapter
        binding.chatMessageList.layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(baseContext, R.drawable.line_divider)!!
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