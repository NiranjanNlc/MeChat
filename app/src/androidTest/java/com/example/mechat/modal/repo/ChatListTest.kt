package com.example.mechat.modal.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ChatListTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private lateinit var receiverPathSring : String


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @Before
    fun setupViewModel(){
        runBlocking {

        }
    }
    @Test
    fun testToGetListOfChat(){
        runBlocking {
            UserListService.getChatListFromDb()
        }
        assert(UserListService.chatList.value!=null)
    }
}