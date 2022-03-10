package com.example.mechat.modal.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mechat.viewmodal.ChatViewModal
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ChatViewModalTest

{

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    val viewModel = ChatViewModal()

    @Before
    fun swtUp()
    {

    }

    @Test
    fun   testTheSendingOperationEmptymessageShouldReturnError()
    {
        runBlocking {
            viewModel.sendMessage()
        }

    }

    @Test
    fun   testTheSendingOperationWithMessageShouldUpdateMessageList()
    {
        runBlocking {
            viewModel.sendMessage()
        }
    }

}