package com.example.mechat.viewmodal

import androidx.lifecycle.ViewModel
import com.example.mechat.modal.repo.UserListService
import com.firebase.ui.auth.data.model.User
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeViewModal: ViewModel(),CoroutineScope
{

    val chatList= UserListService.chatList
    val userList = UserListService.userList
    val loginedUser = UserListService.user
    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + SupervisorJob()


    init{
        //userList = UserListService.userList
        // UserListService.getListOfUser()
        getUserList()
        getChatList()
    }

    private fun getChatList() {
        this.launch {
            withContext(
                coroutineContext
            ) {
                UserListService.getListOfChats()
            }
        }
    }

    private fun getUserList() {
        this.launch {
            withContext(
                coroutineContext
            ) {
                UserListService.getListOfUser()
            }
        }
    }
    fun getLogineduseer() {
            this.launch {
                withContext(coroutineContext
                ) {
                    UserListService.getUserFromDb()
                }
            }

}
}