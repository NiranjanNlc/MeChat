package com.example.mechat.viewmodal

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mechat.modal.repo.SettingService
import com.example.mechat.utils.FirebaseUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SettingViewModal : ViewModel(), CoroutineScope {


    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + SupervisorJob()

    val user = SettingService.user
    val profileImageUrl = SettingService.profileImageUrl

    init {
        Log.i(" add user detail ","iniated .... ")
        viewModelScope.launch {
            Log.i(" add user detail ","view modal scope launched  .... ")
            SettingService.getUserFromDb(FirebaseUtils.firebaseUser!!.uid)
        }
    }

    fun updateUserInfo(profileImageUrl: String) {
        viewModelScope.launch {
            SettingService.updateProfileImageUrl(profileImageUrl)
        }
    }

    fun sendImageToFireBase(list: Intent?) {
        viewModelScope.launch {
            SettingService.saveImageFireBaseStorage(list)
        }
    }
}
