package com.example.mechat.modal.data

import android.content.Intent
import android.util.Log
import com.example.mechat.utils.Extensions.toast
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.utils.FirebaseUtils.database

object FireBaseService {
    fun writeNewUser(user: Users) {
        database.child("users").child(user.userId).setValue(user)
    }

    fun sighnUpUser(user: Users): Boolean {
        var success: Boolean =false;
        FirebaseUtils.firebaseAuth.createUserWithEmailAndPassword(user.mail, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    writeNewUser(user)
                    success = true
                } else {
                    Log.d(" FAilure ", " could not cretate user at givrn ")
                    success = false
                }

            }
    return success
}
}