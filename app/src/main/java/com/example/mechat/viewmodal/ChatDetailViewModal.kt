package com.example.mechat.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mechat.modal.repo.ReceivemessageService
import com.example.mechat.modal.repo.SendMessageService
import com.google.firebase.auth.FirebaseAuth

class ChatDetailViewModal : ViewModel()
{
    var _messageInput = MutableLiveData<String>()
    val getUserInput: LiveData<String> = _messageInput
     var recieverId = MutableLiveData<String>()
    val senderId = MutableLiveData<String>()
    val messageList = ReceivemessageService.chatmessgaes
    init {
        refreshMessgaeList()
    }
    fun refreshMessgaeList()
    {
        ReceivemessageService.getMessageList(senderId.value.toString(),recieverId.value.toString())
    }
    fun sendMessage( )
    {
        println(" sending messages ............." + _messageInput)
        performSendMessage()
        refreshMessgaeList()
    }

    private fun performSendMessage() {
        if (_messageInput.value.isNullOrEmpty()) {
            println(" empty string ...........")
             return
        }
        println(" performing sending message ................")
        SendMessageService.sendMessage(senderId.value.toString(),recieverId.value.toString(), _messageInput.value.toString())
        _messageInput.value = " "
    }

    fun setSenderReceiver(senderid: String?, recieverid: String?) {
        senderId.value = senderid
        recieverId.value = recieverid
    }
}

