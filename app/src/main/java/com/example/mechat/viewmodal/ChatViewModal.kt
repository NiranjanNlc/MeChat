package com.example.mechat.viewmodal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mechat.modal.data.ChatMessage
import com.example.mechat.modal.data.Users
import com.example.mechat.modal.repo.ChatService
import com.example.mechat.modal.repo.UserListService
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatViewModal : ViewModel()
{
     var receiver = MutableLiveData<Users>()
    val logIneduser = UserListService.user
    var messageInput = MutableLiveData<String>()
    var receiverId = MutableLiveData<String>()
    val messageList = ChatService.chatmessgaes
    init {
      //  refreshMessgaeList()
    }
    fun refreshMessgaeList()
    {
        println(" ${logIneduser.value?.userId} is sdnding message to ${receiverId.value}")
        viewModelScope.launch {
            ChatService.getMessageList(logIneduser.value?.userId.toString(),receiverId.value.toString())
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
            receiverId.value,
            System.currentTimeMillis()/1000)
        viewModelScope.launch {
            ChatService.sendMessage(chatMessage)
        }
    }

    fun getLogineduseer() {
        viewModelScope.launch {
            withContext(coroutineContext
            ) {
                UserListService.getUserFromDb()
            }
        }
    }
    fun setSenderReceiver()
    {
        receiver.value?.let { ChatService.setSenderReceiver(it) }
    }
}


