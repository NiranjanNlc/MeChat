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
    private lateinit  var result : AuthResult
    suspend fun writeNewUser(user: Users):Boolean
    {
        return try{
            val data =  FirebaseUtils.firebaseAuth.uid?.let { database.child("users").child(it).setValue(user) }
                ?.await()
            println(" dta base result $data.toString()")
            return true
        }catch (e : Exception){
            println(" Sghnng up excepto ${e.message}")
            return false
        }
    }

   suspend fun sighnUpUser(user: Users)
    {
         result =  FirebaseUtils.firebaseAuth.
        createUserWithEmailAndPassword(user.mail!!, user.password).await()
            if (result!=null)
            {
                println(result.toString())
                writeNewUser(user)
            }
            else{
                Log.i(" error ", " could not sighn up ")
            }
    }
}