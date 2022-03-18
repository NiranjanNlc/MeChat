package com.example.mechat.modal.data

data class ChatMessage(
                  val text: String?=null,
                  val senderId: String?=null,
                  val receiverId: String?=null,
                  val timeStamp: Long=0)

