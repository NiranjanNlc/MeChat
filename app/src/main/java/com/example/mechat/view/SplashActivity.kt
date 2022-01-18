package com.example.mechat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.example.mechat.R
import com.example.mechat.databinding.SplashScreenBinding
import com.example.mechat.utils.FirebaseUtils

class SplashActivity : AppCompatActivity()
{
    private lateinit var i : Intent
    private lateinit var binnding : SplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binnding = DataBindingUtil.setContentView(this, R.layout.splash_screen)
        animate()
        print(" $FirebaseUtils.firebaseUser  user in your town ...")
        transferToNextActivity()

    }

    private fun animate() {
        val mFade: Fade = Fade(Fade.IN)
        TransitionManager.beginDelayedTransition(binnding.splach, mFade)
    }

    private fun transferToNextActivity() {
        if (FirebaseUtils.firebaseUser != null) {
            i = Intent(this, WelecomActivity::class.java)
        } else {
            i = Intent(this, Login::class.java)
        }
        startActivity(i)
        finish()
    }
}