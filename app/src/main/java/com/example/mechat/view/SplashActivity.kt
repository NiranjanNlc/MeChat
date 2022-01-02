package com.example.mechat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechat.utils.FirebaseUtils

class SplashActivity : AppCompatActivity()
{
    private lateinit var i : Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         print(" $FirebaseUtils.firebaseUser  user in your town ...")
       // FirebaseUtils.firebaseAuth.signOut()
        if(FirebaseUtils.firebaseUser!= null)
        {
             i = Intent(this, WelecomActivity::class.java)
        }
        else
        {
            i = Intent(this, Login::class.java)
        }
        startActivity(i)
        finish()

    }
}