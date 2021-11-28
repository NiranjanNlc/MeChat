package com.example.mechat.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mechat.modal.repo.SendMessageService
import com.google.firebase.auth.FirebaseAuth

class ChatDetailViewModal : ViewModel()
{
    //variable that will listen to user's input
    var _messageInput = MutableLiveData<String>()
    //expose the variable to the owner(activity/fragment)
    val getUserInput: LiveData<String> = _messageInput
    //variable that will listen to user's input
    var _recieverId = MutableLiveData<String>()
    //expose the variable to the owner(activity/fragment)
    val receiverId: LiveData<String> = _recieverId

    fun sendMessage( )
    {
        println(" sending messages ............." + _messageInput)
        performSendMessage()
    }

    private fun performSendMessage() {
        if (_messageInput.value.isNullOrEmpty()) {
             return
        }
        val fromId = FirebaseAuth.getInstance().uid ?: return
        val toId = receiverId
        SendMessageService.sendMessage(fromId, toId.value.toString(), _messageInput.value.toString())
    }
    }

