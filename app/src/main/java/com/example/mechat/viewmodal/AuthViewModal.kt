package com.example.mechat.viewmodal

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mechat.modal.repo.Authenciation
import com.example.mechat.modal.data.Users
import kotlinx.coroutines.launch

class AuthViewModal: ViewModel()
{
    lateinit var user :  Users
    var firebaseUser= Authenciation.userLiveData
    var mail= MutableLiveData("niranjannlc10@gmail.com")
    var userName = MutableLiveData("Ashmita Shrestha ")
    var password = MutableLiveData("123456")
    init{
        mail.value = "niranjannlc10@gmail.com"
        userName.value= "Niranjan"
        password.value ="123456"
    }
    fun sighnUp()
    {
        user= Users(userName =userName.value.toString(),mail = mail.value.toString(),password =password.value.toString() )
        Log.i("user ",user.toString())
        viewModelScope.launch {
            Authenciation.sighnUpUser(user)
        }
    }

    fun logOut() {
        Authenciation.logOut()
    }

}