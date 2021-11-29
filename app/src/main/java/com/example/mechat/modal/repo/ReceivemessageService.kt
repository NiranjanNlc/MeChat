package com.example.mechat.modal.repo

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechat.modal.data.ChatMessage
import com.example.mechat.utils.FirebaseUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

object ReceivemessageService
{
    var chatmessgaes = MutableLiveData<List<ChatMessage>>()
    fun getMessageList( fromId : String , toId : String )
    {
        val myTopPostsQuery = FirebaseUtils.database.child("/user-messages/$fromId/$toId").orderByChild("timeStamp")
        myTopPostsQuery.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot)
            {
                val list = snapshot.children.map { it.getValue(ChatMessage::class.java)!! }
                Log.d("TAG", "Value is: $list")
                Log.i(" messageist 00", snapshot.getValue().toString())
                chatmessgaes.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i(" error database ", error.details)
            }

        })
    }
}