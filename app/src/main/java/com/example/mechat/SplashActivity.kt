package com.example.mechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechat.R
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.view.ChatDetailActivity
import com.example.mechat.view.Login
import com.example.mechat.view.WelecomActivity

class SplashActivity : AppCompatActivity()
{
    private lateinit var i : Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         print(" $FirebaseUtils.firebaseUser  user in your town ...")
        if(FirebaseUtils.firebaseUser!= null)
        {
             i = Intent(this, WelecomActivity::class.java)
        }
        else
        {
            i = Intent(this, Login::class.java)
        }
        startActivity(i)

    }
}