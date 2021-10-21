package com.example.mechat.modal.data

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechat.utils.Extensions.toast
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.utils.FirebaseUtils.database

object FireBaseService {
    lateinit var success: MutableLiveData<Boolean>;
    fun writeNewUser(user: Users) {
        database.child("users").child(user.userId).setValue(user)
    }

    fun sighnUpUser(user: Users){

        FirebaseUtils.firebaseAuth.createUserWithEmailAndPassword(user.mail, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    writeNewUser(user)
                    success.value = true
                } else {
                    Log.d(" FAilure ", " could not cretate user at givrn ")
                    Log.d(" FAilure ", "${user.mail}")
                    success.value = false
                }
            }
}
}