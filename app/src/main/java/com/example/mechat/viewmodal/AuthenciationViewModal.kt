package com.example.mechat.viewmodal

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mechat.modal.repo.AuthenciationService
import com.example.mechat.modal.data.Users
import kotlinx.coroutines.launch

class AuthenciationViewModal: ViewModel()
{
    lateinit var user :  Users
    var mail= MutableLiveData<String>("niranjannlc10@gmail.com")
    var userName = MutableLiveData<String>(" ]NirnjN")
    var password = MutableLiveData<String>("123456")
    init{
            mail.value = "niranjannlc10@gmail.com0"
            userName.value= " Niranjan "
            password.value ="123456"
    }
    fun sighnUp()
    {
        user= Users(userName =userName.value.toString(),mail = mail.value.toString(),password =password.value.toString() )
        Log.i("user ",user.toString())
        viewModelScope.launch {
            AuthenciationService.sighnUpUser(user)
        }
    }
}