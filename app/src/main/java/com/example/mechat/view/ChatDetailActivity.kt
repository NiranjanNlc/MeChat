package com.example.mechat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.i
import androidx.databinding.DataBindingUtil
import com.example.mechat.R
import com.example.mechat.databinding.ActivityChatDetailBinding
import com.example.mechat.databinding.ActivitySighUpBinding
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.viewmodal.AuthenciationViewModal
import com.example.mechat.viewmodal.ChatDetailViewModal
import com.example.mechat.viewmodal.ViewModalFactory
import java.util.logging.Logger

class ChatDetailActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityChatDetailBinding
    private lateinit var viewModal: ChatDetailViewModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_detail)
        viewModal  = ViewModalFactory().create(ChatDetailViewModal ::class.java)
        binding.viewmodal = viewModal
         var senderId = FirebaseUtils.firebaseUser?.uid
         var recieverId =intent.getStringExtra("userId")
         var userName = intent.getStringExtra("username")
        Log.i(" intent ", " $senderId was  $recieverId  thus $userName")
       // binding.username.text = "Niranjan Lamichhane "
    }
}