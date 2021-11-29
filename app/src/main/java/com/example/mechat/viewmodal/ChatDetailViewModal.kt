package com.example.mechat.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mechat.modal.repo.ReceivemessageService
import com.example.mechat.modal.repo.SendMessageService
import com.google.firebase.auth.FirebaseAuth

class ChatDetailViewModal : ViewModel()
{
    //variable that will listen to user's input
    var _messageInput = MutableLiveData<String>()
    //expose the variable to the owner(activity/fragment)
    val getUserInput: LiveData<String> = _messageInput
     //sender and receiver consideration
     val recieverId = MutableLiveData<String>()
    val senderId = MutableLiveData<String>()

    //List Of messages
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
             return
        }
        val fromId = senderId
        val toId = recieverId
        SendMessageService.sendMessage(fromId.value.toString(), toId.value.toString(), _messageInput.value.toString())
    }
    }

