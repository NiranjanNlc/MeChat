package com.example.mechat.modal.repo

import com.example.mechat.modal.data.ChatMessage

interface ChatOperation {
   // public fun sendMessage(receiverId : String, senderId : String , text : String)
    public suspend fun getMessageList(senderId : String, receiverId : String)
    suspend fun sendMessage(chatMessage1: ChatMessage)
}