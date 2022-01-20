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
       try {
           result =  FirebaseUtils.firebaseAuth.
           createUserWithEmailAndPassword(user.mail!!, user.password).await()
       }
       catch (e:Exception)
       {
           Log.i("SghnUpErro",e.message.toString())
       }
            if (result.user!=null)
            {
                println(result.toString())
                user.userId = result.user!!.uid
                writeNewUser(user)
            }
            else{
                Log.i(" error ", " could not sighn up ")
            }
    }
}