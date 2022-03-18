package com.example.mechat.modal.repo

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mechat.modal.data.ChatMessage
import com.example.mechat.utils.FirebaseUtils
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SendMessageServiceTest
{
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private lateinit var receiverPathSring : String


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val chatMessage = ChatMessage(
        " HelloAasshmab ",
        "123456",
        "12345678",
        System.currentTimeMillis()/1000
    )

    @Before
    fun setupViewModel(){
        runBlocking {
            ChatService.sendMessage(chatMessage)
        }
    }

    @Test
    fun verifyUserLastMessengersSenderReceiver()
    {
        val message= runBlocking {
            getEndPointMessage(chatMessage.senderId.toString(), chatMessage.receiverId!!)
        }
        val message1= runBlocking {
        getEndPointMessage(chatMessage.senderId.toString(), chatMessage.receiverId!!)
    }
        Log.i(" message posted ",message.toString())
        assertEquals(message1, message )

    }
    @Test
    fun verifyUSenderEndIsUpdated()
    {
        val message= runBlocking {
            getEndPointMessage(chatMessage.senderId.toString(), chatMessage.receiverId!!)
        }
        Log.i(" message posted ",message.toString())
        assertEquals(chatMessage, message )
    }
    @Test
    fun verifyReceiverEndIsUpdated()
    {

        val message= runBlocking {
            getEndPointMessage(chatMessage.receiverId.toString(), chatMessage.senderId!!)
        }
        Log.i(" message posted ",message.toString())
        assertEquals(chatMessage, message )
    }
    @Test
    fun verifyGetMessageListIsWorking()
    {
        val message= runBlocking {
            getEndPointMessages(chatMessage.senderId.toString(), chatMessage.receiverId!!)
        }
        runBlocking {
            ChatService.getMessageList(chatMessage.senderId.toString(), chatMessage.receiverId!!)
        }
        val message1= ChatService.chatmessgaes.value
        Log.i(" message posted ",message.toString())
        assertEquals(message1, message )
    }

    suspend fun getEndPointMessage(senderId: String, receiverId: String): ChatMessage? {
        receiverPathSring ="$receiverId/$senderId"
        val data = FirebaseUtils.database.child("/user-messages/$receiverPathSring").get().await()
        Log.i(" data manipulated ",data.toString())
        val messages = data.children.map {it.getValue(ChatMessage::class.java)!! }
        return messages.find { it.timeStamp == chatMessage.timeStamp}
    }

    suspend fun getEndPointMessages(senderId: String, receiverId: String): List<ChatMessage> {
        receiverPathSring ="$receiverId/$senderId"
        val data = FirebaseUtils.database.child("/user-messages/$receiverPathSring").get().await()
        Log.i(" data manipulated ",data.toString())
        val messages = data.children.map {it.getValue(ChatMessage::class.java)!! }
        return messages
    }
    @Test
    fun verifyGetChatistIsWorking()
    {

        runBlocking {
            UserListService.getListOfChats()
        }

    }
}