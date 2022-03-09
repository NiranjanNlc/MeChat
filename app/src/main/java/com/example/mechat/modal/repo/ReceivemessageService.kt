package com.example.mechat.modal.repo

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechat.modal.data.ChatMessage
import com.example.mechat.utils.FirebaseUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object ReceivemessageService
{
    var chatmessgaes = MutableLiveData<List<ChatMessage>>()
    val sendReceiveRef = FirebaseUtils.database.child("/user-messages/")
    private lateinit var receiverPathSring : String

    @SuppressLint("RestrictedApi")
    fun getMessageList(senderId : String, receiverId : String )
    {
        receiverPathSring ="$receiverId/$senderId"
        println(" from id to $senderId going to $receiverId")

        val myTopPostsQuery = sendReceiveRef.child("$senderId/$receiverId")
                                            .orderByChild("timeStamp")
        println(" Path restored in ${myTopPostsQuery.path} "  )

        myTopPostsQuery.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot)
            {
                val list = snapshot.children.map { it.getValue(ChatMessage::class.java)!! }
                Log.d("TAG", "Value is: $list")
                Log.i(" messageist 00", snapshot.value.toString())
                chatmessgaes.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i(" error database ", error.details)
            }

        })
    }
}