package com.example.mechat.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mechat.modal.data.ChatMessage
import com.example.mechat.modal.repo.ChatService
import kotlinx.coroutines.launch
import java.util.*

class ChatViewModal : ViewModel()
{
    var messageInput = MutableLiveData<String>()
    var recieverId = MutableLiveData<String>()
    val senderId = MutableLiveData<String>()
    val messageList = ChatService.chatmessgaes
    init {
        refreshMessgaeList()
    }
    fun refreshMessgaeList()
    {
        println(" ${senderId.value} is sdnding message to ${recieverId.value}")
        viewModelScope.launch {
            ChatService.getMessageList(senderId.value.toString(),recieverId.value.toString())
        }
    }
    fun sendMessage( )
    {
        println(" sending messages .............$messageInput")
        performSendMessage()
        refreshMessgaeList()
    }

    private fun performSendMessage() {
        val chatMessage = ChatMessage(UUID.randomUUID().toString(),
            messageInput.value,
            senderId.value,
            recieverId.value,
            System.currentTimeMillis()/1000)
        viewModelScope.launch {
            ChatService.sendMessage(chatMessage)
        }
    }

    fun setSenderReceiver(senderid: String?, recieverid: String?) {
        senderId.value = senderid!!
        recieverId.value = recieverid!!
    }
}

