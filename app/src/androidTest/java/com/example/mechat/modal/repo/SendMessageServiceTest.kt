package com.example.mechat.modal.repo

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mechat.modal.data.ChatMessage
import com.example.mechat.modal.data.Users
import com.example.mechat.utils.FirebaseUtils
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.tasks.await
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class SendMessageServiceTest1 : TestCase()
{
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setupViewModel(){

    }

    @Test
    fun verifyUserLastMessengersSenderReceiver()
    {


    }
    @Test
    fun verifyUSenderEndIsUpdated()
    {

    }
    @Test
    suspend fun verifyReceiverEndIsUpdated()
    {

    }

    suspend fun getEndPointMessage(): List<ChatMessage> {
        val data = FirebaseUtils.database.child("users").get().await()
        Log.i(" data manipulated ",data.toString())
        return  data.children.map {it.getValue(ChatMessage::class.java)!!
        }
    }
}