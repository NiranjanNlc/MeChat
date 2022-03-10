package com.example.mechat.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModalFactory : ViewModelProvider.AndroidViewModelFactory()
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModal::class.java) -> {
                AuthViewModal()as T
            }
            modelClass.isAssignableFrom(HomeViewModal::class.java) -> {
                HomeViewModal()as T
            }
            modelClass.isAssignableFrom(ChatViewModal::class.java) -> {
                ChatViewModal()as T
            }
            modelClass.isAssignableFrom(SettingViewModal::class.java) -> {
                SettingViewModal() as T
            }
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}
