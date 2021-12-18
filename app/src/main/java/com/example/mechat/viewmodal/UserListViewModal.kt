package com.example.mechat.viewmodal

import androidx.lifecycle.ViewModel
import com.example.mechat.modal.repo.UserListService

class UserListViewModal: ViewModel()
{

    var userList = UserListService.userList

    init{
        userList = UserListService.userList
        UserListService.getListOfUser()
    }

}