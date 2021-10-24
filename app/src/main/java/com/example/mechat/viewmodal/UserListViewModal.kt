package com.example.mechat.viewmodal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mechat.modal.data.Users
import com.example.mechat.modal.repo.FireBaseRepo

class UserListViewModal: ViewModel()
{

    var userList = FireBaseRepo.userList

    init{
        FireBaseRepo.getUserList()
    }

}