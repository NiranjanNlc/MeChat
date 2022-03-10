
package com.example.mechat.modal.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechat.modal.data.Users
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.utils.FirebaseUtils.database
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

object Authenciation {

    private lateinit  var result : AuthResult
    val userLiveData: MutableLiveData<FirebaseUser> = MutableLiveData()
    private val loggedOutLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val firebaseAuth: FirebaseAuth = FirebaseUtils.firebaseAuth

    private suspend fun writeNewUser(user: Users):Boolean
    {
        return try{
            val data =  FirebaseUtils.firebaseAuth.uid?.let { database.child("users").child(it).setValue(user) }
                ?.await()
            println(" dta base result $data.toString()")
            userLiveData.postValue(firebaseAuth.currentUser)
            true
        }catch (e : Exception){
            println(" Sghnng up excepto ${e.message}")
            false
        }
    }

    suspend fun signUpUser(user: Users)
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
    init {

        if (firebaseAuth.currentUser != null) {
            loggedOutLiveData.postValue(false)
        }

    }

    fun logOut() {

        firebaseAuth.signOut()

        loggedOutLiveData.postValue(true)

    }
}