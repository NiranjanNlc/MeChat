package com.example.mechat.modal.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechat.modal.data.Users
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.utils.FirebaseUtils.database

object AuthenciationService {
    var success= MutableLiveData<Boolean>(false);
     var  process = MutableLiveData<Boolean>(false)
    fun writeNewUser(user: Users)
    {
        FirebaseUtils.firebaseAuth.uid?.let { database.child("users").child(it).setValue(user) }
    }

    fun sighnUpUser(user: Users) {
             FirebaseUtils.firebaseAuth.createUserWithEmailAndPassword(user.mail, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful)
                    {
                        writeNewUser(user)
                     //   process.value =true
                        success.value = true
                    } else {
                        Log.d(" FAilure ", " could not cretate user at givrn ")
                        Log.d(" FAilure ", "${user.mail}")
                       // process.value =true
                        success.value = false
                    }
                }
    }


}