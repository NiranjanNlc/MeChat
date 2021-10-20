package com.example.mechat.viewmodal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mechat.modal.data.FireBaseService
import com.example.mechat.modal.data.Users

class AuthenciationViewModal: ViewModel()
{
    lateinit var user :  Users
    var mail= MutableLiveData<String>("niranjannlc10@gmail.com")
    var userName = MutableLiveData<String>(" NirnjN")
    var password = MutableLiveData<String>("123456")

    init{

    }
    fun sighnUp(): Boolean?
    {

        user= Users(userName =userName.value.toString(),mail = mail.value.toString(),password =password.value.toString() )
        Log.i("user ",user.toString())
       return FireBaseService.sighnUpUser(user)
    }
}