package com.example.mechat.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mechat.modal.data.FireBaseService
import com.example.mechat.modal.data.Users

class AuthenciationViewModal: ViewModel()
{
    lateinit var user :  Users
    var mail= MutableLiveData<String>()
    var userName = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    init{

    }
    fun sighnUp(): Boolean?
    {
        user= Users(userName =userName.value.toString(),mail = mail.value.toString(),password =password.value.toString() )
       return FireBaseService.sighnUpUser(user)
    }
}