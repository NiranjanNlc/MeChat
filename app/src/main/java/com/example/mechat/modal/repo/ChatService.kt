package com.example.mechat.modal.repo

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechat.modal.data.ChatMessage
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

object ChatService : ChatOperation {
    var chatmessgaes = MutableLiveData<List<ChatMessage>>()
    val sendReceiveRef = FirebaseDatabase.getInstance().getReference("/user-messages/")
    val lsestMessageRef = FirebaseDatabase.getInstance().getReference("/latest-messages/")
    private lateinit var chatMessage: ChatMessage
    private lateinit var senderPathString: String
    private lateinit var receiverPathSring: String
    override fun sendMessage(receiverId: String, senderId: String, text: String) {
        senderPathString = "$senderId/$receiverId"
        receiverPathSring = "$receiverId/$senderId"

        chatMessage = ChatMessage(
            "reference.key!!", text,
            senderId,
            receiverId, System.currentTimeMillis() / 1000
        )
        updateSenderRefrence()
        updateReceiverRefrence()
        updateLatestMessage()

    }
    override fun sendMessage(chatMessage1: ChatMessage) {
        senderPathString = "${chatMessage1.senderId}/${chatMessage1.receiverId}"
        receiverPathSring = "${chatMessage1.receiverId}/${chatMessage1.senderId}"
        chatMessage =chatMessage1
        updateSenderRefrence()
        updateReceiverRefrence()
        updateLatestMessage()

    }


    private fun updateLatestMessage() {
        val senderLastMsg = lsestMessageRef.child(senderPathString)
        senderLastMsg.setValue(chatMessage)
        val receiverLastMsg = lsestMessageRef.child(receiverPathSring)
        receiverLastMsg.setValue(chatMessage)
    }

    private fun updateReceiverRefrence() {
        val toReference = sendReceiveRef.child(receiverPathSring).push()
        toReference.setValue(chatMessage)
    }

    private fun updateSenderRefrence() {
        val reference = sendReceiveRef.child(senderPathString).push()
        reference.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d(TAG, "Saved our chat message: ${reference.key}")
            }
    }


    override suspend fun getMessageList(senderId: String, receiverId: String) {
        receiverPathSring = "$receiverId/$senderId"
        println(" from id to $senderId going to $receiverId")

        val myTopPostsQuery =  sendReceiveRef.child("$senderId/$receiverId")
            .get().
            await()
        println(" Path restored in ${myTopPostsQuery.children} ")
        chatmessgaes.value = myTopPostsQuery.children.map {it.getValue(ChatMessage::class.java)!! }

    }

}