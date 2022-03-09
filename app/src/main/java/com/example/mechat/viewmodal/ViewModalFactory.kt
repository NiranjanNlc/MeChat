package com.example.mechat.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModalFactory : ViewModelProvider.AndroidViewModelFactory()
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthenciationViewModal::class.java) -> {
                AuthenciationViewModal()as T
            }
            modelClass.isAssignableFrom(UserListViewModal::class.java) -> {
                UserListViewModal()as T
            }
            modelClass.isAssignableFrom(ChatDetailViewModal::class.java) -> {
                ChatDetailViewModal()as T
            }
            modelClass.isAssignableFrom(UserDetailViewModal::class.java) -> {
                UserDetailViewModal() as T
            }
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}
