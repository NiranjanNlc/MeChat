package com.example.mechat.modal.repo

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mechat.modal.data.ChatMessage
import com.google.firebase.database.FirebaseDatabase

object SendMessageService
{

    fun sendMessage(fromId : String, receiverId : String , text : String)
    {
        val reference = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$receiverId").push()
        val toReference = FirebaseDatabase.getInstance().getReference("/user-messages/$receiverId/$fromId").push()

        val chatMessage = ChatMessage(reference.key!!, text, fromId, receiverId, System.currentTimeMillis() / 1000)
        reference.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d(TAG, "Saved our chat message: ${reference.key}")
            }
        
        toReference.setValue(chatMessage)
        val latestMessageRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId/$receiverId")
        latestMessageRef.setValue(chatMessage)
        val latestMessageToRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$receiverId/$fromId")
        latestMessageToRef.setValue(chatMessage)
    }
}