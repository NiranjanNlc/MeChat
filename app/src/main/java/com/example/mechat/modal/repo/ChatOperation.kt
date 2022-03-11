package com.example.mechat.modal.repo

import com.example.mechat.modal.data.ChatMessage

interface ChatOperation {

    suspend fun getMessageList(senderId : String, receiverId : String)
    suspend fun sendMessage(chatMessage1: ChatMessage)
}