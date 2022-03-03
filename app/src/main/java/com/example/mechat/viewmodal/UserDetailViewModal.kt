package com.example.mechat.viewmodal

import androidx.lifecycle.ViewModel
import com.example.mechat.modal.repo.UserListService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class UserDetailViewModal : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + SupervisorJob()
}
