package com.example.mechat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.i
import com.example.mechat.R
import com.example.mechat.databinding.ActivityChatDetailBinding
import com.example.mechat.utils.FirebaseUtils
import java.util.logging.Logger

class ChatDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
         var senderId = FirebaseUtils.firebaseUser?.uid
         var recieverId =intent.getStringExtra("userId")
         var userName = intent.getStringExtra("username")
        Log.i(" intent ", " $senderId was  $recieverId  thus $userName")
        binding.username.text = recieverId

    }
}