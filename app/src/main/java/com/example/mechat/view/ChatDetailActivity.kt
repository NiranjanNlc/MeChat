package com.example.mechat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechat.R
import com.example.mechat.utils.FirebaseUtils

class ChatDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_detail)
         var senderId = FirebaseUtils.firebaseUser?.uid
         var recieverId =intent.getStringExtra("userId")
         var userName = intent.getStringExtra("username")
    }
}