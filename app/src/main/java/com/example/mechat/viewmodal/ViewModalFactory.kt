package com.example.mechat.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModalFactory :ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthenciationViewModal::class.java)) {
            return AuthenciationViewModal()as T
        }
        else if (modelClass.isAssignableFrom(UserListViewModal::class.java)) {
            return UserListViewModal()as T
        } else if (modelClass.isAssignableFrom(ChatDetailViewModal::class.java)) {
            return ChatDetailViewModal()as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
    }
