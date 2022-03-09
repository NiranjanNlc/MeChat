package com.example.mechat.modal.repo

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mechat.modal.data.ChatMessage
import com.google.firebase.database.FirebaseDatabase

object SendMessageService
{
    val sendReceiveRef = FirebaseDatabase.getInstance().getReference("/user-messages/")
    val lsestMessageRef = FirebaseDatabase.getInstance().getReference("/latest-messages/")
    private lateinit var chatMessage :ChatMessage
    private lateinit var senderPathString: String
    private lateinit var receiverPathSring : String
    fun sendMessage(receiverId : String, senderId : String , text : String)
    {
        senderPathString = "$senderId/$receiverId"
        receiverPathSring ="$receiverId/$senderId"

        chatMessage = ChatMessage("reference.key!!", text,
            senderId,
            receiverId, System.currentTimeMillis() / 1000)
        updateSenderRefrence()
        updateReceiverRefrence()
        updaeLatestMessage()
       
    }

    private fun updaeLatestMessage() {
        val senderLastMsg = lsestMessageRef.child(senderPathString )
        senderLastMsg.setValue(chatMessage)
        val receiverLastMsg = lsestMessageRef.child(receiverPathSring )
        receiverLastMsg.setValue(chatMessage)
    }

    private fun updateReceiverRefrence() {
        val toReference = sendReceiveRef.child(receiverPathSring ).push()
        toReference.setValue(chatMessage)
    }

    private fun updateSenderRefrence() {
        val reference = sendReceiveRef.child(senderPathString ).push()
        reference.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d(TAG, "Saved our chat message: ${reference.key}")
            }
    }
}