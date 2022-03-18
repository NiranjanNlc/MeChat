package com.example.mechat.modal.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechat.modal.data.Chats
import com.example.mechat.modal.data.Users
import com.example.mechat.utils.FirebaseUtils
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import kotlinx.coroutines.tasks.await

object UserListService
{
    val chatList = MutableLiveData<List<Chats>>()
    var userList = MutableLiveData<List<Users>>()
    var user = MutableLiveData<Users>()
    val uid = FirebaseUtils.sender

    private suspend fun getUserListFromDb(): Any?
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
    suspend fun getUserFromDb(){
        Log.i(" add user detail ","get user from db  .... ")
        return try{
            val data =FirebaseUtils.database.child("users/${uid}/").get().await()
            Log.i(" add user detail ","user $data ")
            Log.i(" data manipulated ",data.toString())
            user.value =data.getValue(Users ::class.java)
        }
        catch (e : Exception)
        {
            println( " eoor encountered during the operation " + e.message)
        }
    }
      suspend fun getChatListFromDb(): Any?
    {
        println(" $uid" )
        Log.i(" data manipulated chat","$uid")

        return try{
            val data =FirebaseUtils.database.child("latest-messages/")
                .child("${uid}/").get().await()

            Log.i(" data manipulated chat",data.toString())
            Log.i(" data manipulated chat",data.ref.toString())
            Log.i(" data d chat",data.getValue().toString())
            val jsonString = data.value as HashMap<*, *>
            Log.i("  required data sets ",jsonString.get("{$uid}").toString())

            data.children.map {it.getValue(Chats::class.java)!!  }
        }catch (e : Exception)
        {
            Log.i( " eoor " ,"encountered during the operation $e.message")
            null
        }
    }
    suspend fun getListOfChats()
    {
        val list = getChatListFromDb()
        Log.d("TAG", "Value is: $list")
        Log.i(" user list 00", list.toString())
        chatList.value = list as List<Chats>?
    }
}