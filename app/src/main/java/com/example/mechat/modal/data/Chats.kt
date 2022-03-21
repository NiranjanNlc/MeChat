package com.example.mechat.modal.data

import kotlinx.serialization.Serializable

@Serializable
data class Chats(
    val timeStamp: Long? =null,
    var interlocutor:Users?=null,
    val text:String?=null
            )