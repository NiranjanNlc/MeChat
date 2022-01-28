package com.example.mechat.modal.repo

import androidx.lifecycle.MutableLiveData

import com.google.firebase.auth.FirebaseUser

import android.widget.Toast 

import com.google.firebase.auth.FirebaseAuth

import android.app.Application
import kotlinx.coroutines.tasks.await


class AuthRepo(private val application: Application) {
    private val firebaseAuth: FirebaseAuth
    val userLiveData: MutableLiveData<FirebaseUser>
    val loggedOutLiveData: MutableLiveData<Boolean>

    suspend fun login(email: String?, password: String?) {
        firebaseAuth.signInWithEmailAndPassword(email!!, password!!).await()
    }

    suspend fun register(email: String?, password: String?) {
        firebaseAuth.createUserWithEmailAndPassword(email!!, password!!).await()
    }

    fun logOut() {
        firebaseAuth.signOut()
        loggedOutLiveData.postValue(true)
    }

    init {
        firebaseAuth = FirebaseAuth.getInstance()
        userLiveData = MutableLiveData()
        loggedOutLiveData = MutableLiveData()
        if (firebaseAuth.currentUser != null) {
            userLiveData.postValue(firebaseAuth.currentUser)
            loggedOutLiveData.postValue(false)
        }
    }

    @JvmName("getUserLiveData1")
    fun getUserLiveData(): MutableLiveData<FirebaseUser> {
        return userLiveData
    }

    @JvmName("getLoggedOutLiveData1")
    fun getLoggedOutLiveData(): MutableLiveData<Boolean> {
        return loggedOutLiveData
    }
}
