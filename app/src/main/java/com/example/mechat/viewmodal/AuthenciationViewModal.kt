package com.example.mechat.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mechat.modal.data.FireBaseService
import com.example.mechat.modal.data.Users

class AuthenciationViewModal: ViewModel()
{
    var user : LiveData<Users> =MutableLiveData<Users>()
    init{

    }
    fun sighnUp(): Boolean?
    {
       return user.value?.let { FireBaseService.sighnUpUser(it) }
    }
}