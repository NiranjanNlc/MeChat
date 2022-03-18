package com.example.mechat.modal.data

import kotlinx.serialization.Serializable

@Serializable
data class Chats(
    val timeStamp: Long? =null,
    val senderId:String?=null,
    val receiverId: String?=null,
    val text:String?=null
            )