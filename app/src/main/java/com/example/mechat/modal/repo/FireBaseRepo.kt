package com.example.mechat.modal.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechat.modal.data.Users
import com.example.mechat.utils.FirebaseUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

object FireBaseRepo
{

     var userList = MutableLiveData<List<Users>>()
    fun getUserList()
    {
        val myTopPostsQuery = FirebaseUtils.database.child("user").orderByChild("username")
        myTopPostsQuery.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot)
            {
             userList.value = snapshot.children as List<Users>?
            }

            override fun onCancelled(error: DatabaseError) {
               Log.i(" error database ", error.details)
            }

        })
    }
}