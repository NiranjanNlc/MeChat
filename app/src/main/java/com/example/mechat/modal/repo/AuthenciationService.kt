package com.example.mechat.modal.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechat.modal.data.Users
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.utils.FirebaseUtils.database
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

object AuthenciationService {
    var success= MutableLiveData<Boolean>(false);
     var  process = MutableLiveData<Boolean>(false)
     private lateinit  var result : AuthResult
    suspend fun writeNewUser(user: Users)
    {
        FirebaseUtils.firebaseAuth.uid?.let { database.child("users").child(it).setValue(user) }
            ?.await()
        success.value=true
        process.value= true
    }

    fun sighnUpUser(user: Users)
    {
        GlobalScope.async{
         result =  FirebaseUtils.firebaseAuth.
        createUserWithEmailAndPassword(user.mail, user.password).await()
            if (result!=null)
            {
                writeNewUser(user)
            }
            else{
                Log.i(" error ", " could not sighn up ")
            }
        }

    }
}