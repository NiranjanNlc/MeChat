package com.example.mechat.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechat.databinding.ActivityChatDetailBinding
import com.example.mechat.modal.data.ChatMessage
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.view.adapter.ChatAdapter
import com.example.mechat.viewmodal.ChatViewModal
import com.example.mechat.viewmodal.ViewModalFactory
import androidx.recyclerview.widget.DividerItemDecoration

import androidx.core.content.ContextCompat
import coil.load
import com.example.mechat.R
import com.example.mechat.modal.data.Users
import com.example.mechat.utils.Extensions.toast


class ChatActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityChatDetailBinding
    private lateinit var viewModal: ChatViewModal
    private lateinit var adapter: ChatAdapter
    private lateinit var receiver: Users
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_detail)
        viewModal  = ViewModalFactory().create(ChatViewModal ::class.java)
        binding.viewmodal = viewModal
        receiver = intent.extras?.get("receiver") as Users
        setSenderReceiver(intent.extras?.get("receiver") as Users)
        obserVeViewModel()
        viewModal.getLogineduseer()
        viewModal.refreshMessgaeList()
    }

    private fun initializeAdapter(messages: List<ChatMessage>) {
        adapter= ChatAdapter(this,messages )
    }

    private fun obserVeViewModel() {
        viewModal.messageList.observe(this) {
            setSenderReceiver(intent.extras?.get("receiver") as Users)
            initializeAdapter(it)
            initializeRecyclerView()
        }
        viewModal.logIneduser.value?.userId?.let { Log.i("profilePic", it) }

        binding.profileImage.load(receiver.profilePic)
    }

    private fun setSenderReceiver(user: Users) {
        val senderId = FirebaseUtils.firebaseUser?.uid
        val recieverId = intent.getStringExtra("userId")
        val userName = intent.getStringExtra("userName")
        Log.i("Send", " $senderId was  $recieverId  thus $userName")
        Log.i("Receiver", " Nlc please $user")
        binding.username.text = userName
        binding.viewmodal?.recieverId?.value = receiver.userId
      //  viewModal.setSenderReceiver(user.userId)
        binding.backArrow.setOnClickListener {
            reverseBackToUserList()
        }
        binding.sendMessage.setOnClickListener {
            sendngMessage()
        }
    }

     private fun initializeRecyclerView() {
        binding.chatMessageList.adapter = adapter
        binding.chatMessageList.layoutManager = LinearLayoutManager(this,
                                                                    LinearLayoutManager.VERTICAL,
                                                        false)
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
        binding.editTextTextMultiLine.text.clear()
    }
    private fun reverseBackToUserList() {
        startActivity(Intent(this, Home::class.java))
        toast("Logged out sucess fully ")
        finish()
    }
}