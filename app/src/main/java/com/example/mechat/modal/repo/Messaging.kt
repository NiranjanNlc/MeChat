package com.example.mechat.modal.repo

import androidx.lifecycle.MutableLiveData
import com.example.mechat.modal.data.ChatMessage
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class Messaging:ChatOperation {
    var chatmessgaes = MutableLiveData<List<ChatMessage>>()
    val sendReceiveRef = FirebaseDatabase.getInstance().getReference("/user-messages/")
    val lsestMessageRef = FirebaseDatabase.getInstance().getReference("/latest-messages/")
    private lateinit var chatMessage: ChatMessage
    private lateinit var senderPathString: String
    private lateinit var receiverPathSring: String

    override suspend fun getMessageList(senderId: String, receiverId: String) {

    }

    override suspend fun sendMessage(chatMessage1: ChatMessage) {
        chatMessage = chatMessage1
    updateSenderRefrence()
    updateReceiverRefrence()
    updateLatestMessage()
    getMessageList(chatMessage1.senderId!!, chatMessage1.receiverId!!)
    }

    override suspend fun updateSenderRefrence() {
        sendReceiveRef.child(senderPathString).push().setValue(chatMessage).await()
    }

    override suspend fun updateReceiverRefrence() {
        sendReceiveRef.child(senderPathString).push().setValue(chatMessage).await()
    }

    override fun updateLatestMessage() {
     }

    override fun getListOfChat() {

    }
}