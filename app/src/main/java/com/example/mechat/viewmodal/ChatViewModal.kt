package com.example.mechat.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mechat.modal.data.ChatMessage
import com.example.mechat.modal.repo.ChatService
import com.example.mechat.modal.repo.UserListService
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ChatViewModal : ViewModel()
{
    val logIneduser = UserListService.user
    var messageInput = MutableLiveData<String>()
    var recieverId = MutableLiveData<String>()
    val messageList = ChatService.chatmessgaes
    init {
        refreshMessgaeList()
    }
    fun refreshMessgaeList()
    {
        println(" ${logIneduser.value?.userId} is sdnding message to ${recieverId.value}")
        viewModelScope.launch {
            ChatService.getMessageList(logIneduser.value?.userId.toString(),recieverId.value.toString())
        }
    }
    fun sendMessage( )
    {
        println(" sending messages .............$messageInput")
        performSendMessage()
        refreshMessgaeList()
    }

    private fun performSendMessage() {
        val chatMessage = ChatMessage(
            messageInput.value,
            logIneduser.value?.userId,
            recieverId.value,
            System.currentTimeMillis()/1000)
        viewModelScope.launch {
            ChatService.sendMessage(chatMessage)
        }
    }

    fun setSenderReceiver( recieverid: String?) {
        recieverId.value = recieverid!!
    }

    fun getLogineduseer() {
        viewModelScope.launch {
            withContext(coroutineContext
            ) {
                UserListService.getUserFromDb()
            }
        }

    }
}


