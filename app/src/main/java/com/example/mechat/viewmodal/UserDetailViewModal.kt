package com.example.mechat.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mechat.modal.repo.AuthenciationService
import com.example.mechat.modal.repo.UserListService
import com.example.mechat.utils.FirebaseUtils
import com.firebase.ui.auth.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserDetailViewModal : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + SupervisorJob()

    val user = UserListService.user

    init {
        viewModelScope.launch {
           UserListService.getUserFromDb(FirebaseUtils.firebaseUser!!.uid)
        }
    }
}
