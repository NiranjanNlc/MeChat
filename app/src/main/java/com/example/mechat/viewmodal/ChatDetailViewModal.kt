package com.example.mechat.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mechat.modal.repo.ReceivemessageService
import com.example.mechat.modal.repo.SendMessageService

class ChatDetailViewModal : ViewModel()
{
    var messageInput = MutableLiveData<String>()
    val getUserInput: LiveData<String> = messageInput
    var recieverId = MutableLiveData<String>()
    val senderId = MutableLiveData<String>()
    val messageList = ReceivemessageService.chatmessgaes
    init {
        refreshMessgaeList()
    }
    fun refreshMessgaeList()
    {
        println(" ${senderId.value} is sdnding message to ${recieverId.value}")
        ReceivemessageService.getMessageList(senderId.value.toString(),recieverId.value.toString())
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
        SendMessageService.sendMessage(senderId.value.toString(),recieverId.value.toString(), messageInput.value.toString())
        messageInput.value = " "
    }

    fun setSenderReceiver(senderid: String?, recieverid: String?) {
        senderId.value = senderid
        recieverId.value = recieverid
    }
}

