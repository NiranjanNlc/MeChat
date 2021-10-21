package com.example.mechat.modal.data

import android.content.Intent
import android.util.Log
import com.example.mechat.utils.Extensions.toast
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.utils.FirebaseUtils.database

object FireBaseService {
    fun writeNewUser(user: Users) {
        Log.d("Writing new user  " , " Niranjan sucess ")
        database.child("users").child(user.userId).setValue(user)
    }

    fun sighnUpUser(user: Users): Boolean {
        var success: Boolean =false;
        FirebaseUtils.firebaseAuth.createUserWithEmailAndPassword(user.mail, user.password)
            .addOnCompleteListener { task ->
                Log.d(" hellp authencaition " , " Niranjan sucess ")
                if (task.isSuccessful) {
                    writeNewUser(user)
                    success = true
                } else {
                    Log.d(" FAilure ", " could not cretate user at givrn ")
                    Log.d(" FAilure ", "${user.mail}")
                    success = false
                }
            }
        Log.i(" suceess value check " , success.toString())
         return success
}
}