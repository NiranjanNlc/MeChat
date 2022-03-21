package com.example.mechat.modal.repo

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechat.modal.data.ChatMessage
import com.example.mechat.modal.data.Chats
import com.example.mechat.modal.data.Users
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

object ChatService : ChatOperation {
    private lateinit var receiver: Users
    private lateinit var sender: Users
    var chatmessgaes = MutableLiveData<List<ChatMessage>>()
    val sendReceiveRef = FirebaseDatabase.getInstance().getReference("/user-messages/")
    val lsestMessageRef = FirebaseDatabase.getInstance().getReference("/latest-messages/")
    private lateinit var chatMessage: ChatMessage
    private lateinit var senderPathString: String
    private lateinit var receiverPathSring: String

    override suspend fun sendMessage(chatMessage1: ChatMessage) {
        senderPathString = "${chatMessage1.senderId}+${chatMessage1.receiverId}"
        receiverPathSring = "${chatMessage1.receiverId}+${chatMessage1.senderId}"
        Log.i(" sender oath  string ", senderPathString)
        Log.i(" receiver oath  string ", receiverPathSring)
        chatMessage =chatMessage1
        updateSenderRefrence()
        updateReceiverRefrence()
        updateLatestMessage()
        getMessageList(chatMessage1.senderId!!,chatMessage1.receiverId!!)
    }


   override fun updateLatestMessage() {
        val senderLastMsg = lsestMessageRef
                            .child("${chatMessage.senderId}")
                            .child("${chatMessage.receiverId}")
       var lastMessage =Chats(timeStamp = chatMessage.timeStamp,
                        interlocutor = receiver,
           text = chatMessage.text)
       senderLastMsg.setValue(lastMessage)
       lastMessage.interlocutor= sender
        val receiverLastMsg = lsestMessageRef
                              .child("${chatMessage.receiverId}")
                             .child("${chatMessage.senderId}")
        receiverLastMsg.setValue(lastMessage)
    }

    override fun getListOfChat() {
        TODO("Not yet implemented")
    }

     override suspend fun updateReceiverRefrence() {
        val toReference = sendReceiveRef.child(receiverPathSring).push()
        toReference.setValue(chatMessage)
    }

   override suspend fun updateSenderRefrence() {
        val reference = sendReceiveRef.child(senderPathString).push()
        reference.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d(TAG, "Saved our chat message: ${reference.key}")
            }
    }


    override suspend fun getMessageList(senderId: String, receiverId: String) {
         receiverPathSring = "$receiverId+$senderId"
        println(" from id to $senderId going to $receiverId")
        Log.i("receiver path String ", receiverPathSring)

        val myTopPostsQuery =  sendReceiveRef.child(receiverPathSring)
            .get().
            await()
        println(" Path restored in ${myTopPostsQuery.children} ")
       try {
           chatmessgaes.value = myTopPostsQuery.children.map {it.getValue(ChatMessage::class.java)!! }
       }
       catch (e:Exception)
       {
           Log.i(" EXCEPTION ", e.message.toString())
       }
    }
    fun setSenderReceiver(receiver1: Users)
    {
        sender = UserListService.user.value!!
        receiver= receiver1
        Log.i(" sender receiver ", " ${sender.userName} is sending message to ${receiver.userName}")
    }
}