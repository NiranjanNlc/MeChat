package com.example.mechat.viewmodal

import androidx.lifecycle.ViewModel
import com.example.mechat.modal.repo.UserListService
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UserListViewModal: ViewModel(),CoroutineScope
{

    val userList = UserListService.userList
    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + SupervisorJob()


    init{
        //userList = UserListService.userList
       // UserListService.getListOfUser()
        getUserList()
    }
    fun getUserList() {
        this.launch {
            withContext(coroutineContext,
                {
                    UserListService.getListOfUser()
                })
        }
    }
}