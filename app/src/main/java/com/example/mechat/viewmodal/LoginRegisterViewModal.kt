package com.example.mechat.viewmodal

import com.google.firebase.auth.FirebaseUser
import androidx.lifecycle.MutableLiveData
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mechat.modal.repo.AuthRepo
import kotlinx.coroutines.launch


class LoginRegisterViewModel(application: Application) :
    AndroidViewModel(application) {
    private val authAppRepository: AuthRepo
    val userLiveData: MutableLiveData<FirebaseUser>

    fun login(email: String?, password: String?) {
        viewModelScope.launch {
            authAppRepository.login(email, password)
        }

    }

    fun register(email: String?, password: String?) {
        viewModelScope.launch {
            authAppRepository.register(email, password)
        }
    }

    init {
        authAppRepository = AuthRepo(application)
        userLiveData = authAppRepository.getUserLiveData()
    }
}