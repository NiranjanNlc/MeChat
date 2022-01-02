package com.example.mechat.modal.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechat.modal.data.Users
import com.example.mechat.utils.FirebaseUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

object UserListService
{
    var userList = MutableLiveData<List<Users>>()
    fun getUserList()
    {
        val myTopPostsQuery = FirebaseUtils.database.child("users").orderByChild("userName")
        myTopPostsQuery.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot)
            {
                val list = snapshot.children.map { it.getValue(Users::class.java)!! }
                Log.d("TAG", "Value is: $list")
                Log.i(" user list 00", snapshot.getValue().toString())
                userList.value = list
            }

            override fun onCancelled(error: DatabaseError) {
               Log.i(" error database ", error.details)
            }

        })
    }
    suspend fun getUserListFromDb(): Any?
    {
        return try{
            val data =FirebaseUtils.database.child("users").get().await()
            Log.i(" data manipulated ",data.toString())
            data.children.map {it.getValue(Users::class.java)!!  }
        }catch (e : Exception)
        {
            println( " eoor encountered during the operation " + e.message)
            null
        }
    }
    suspend fun getListOfUser()
    {
            val list = getUserListFromDb()
            Log.d("TAG", "Value is: $list")
            Log.i(" user list 00", list.toString())
            userList.value = list as List<Users>?

    }
}