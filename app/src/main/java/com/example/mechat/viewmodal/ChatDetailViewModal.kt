package com.example.mechat.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mechat.modal.data.ChatMessage
import com.example.mechat.modal.repo.SendMessageService
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

class ChatDetailViewModal : ViewModel()
{
    var messageInput = MutableLiveData<String>()
    val getUserInput: LiveData<String> = messageInput
    var recieverId = MutableLiveData<String>()
    val senderId = MutableLiveData<String>()
    val messageList = SendMessageService.chatmessgaes
    init {
        refreshMessgaeList()
    }
    fun refreshMessgaeList()
    {
        println(" ${senderId.value} is sdnding message to ${recieverId.value}")
        viewModelScope.launch {
            SendMessageService.getMessageList(senderId.value.toString(),recieverId.value.toString())
        }
    }
    fun sendMessage( )
    {
        println(" sending messages .............$messageInput")
        performSendMessage()
        refreshMessgaeList()
    }

    private fun performSendMessage() {
        if (messageInput.value.isNullOrEmpty()) {
            println(" empty string ...........")
            return
        }
        println(" performing sending message ................")
        val chatMessage = ChatMessage(UUID.randomUUID().toString(),
            messageInput.value,
            senderId.value,
            recieverId.value,
            System.currentTimeMillis()/1000)
        SendMessageService.sendMessage(chatMessage)
        messageInput.value = " "
    }

    fun setSenderReceiver(senderid: String?, recieverid: String?) {
        senderId.value = senderid!!
        recieverId.value = recieverid!!
    }
}

