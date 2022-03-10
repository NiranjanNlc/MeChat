package com.example.mechat.view.activity

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.example.mechat.R
import com.example.mechat.databinding.SplashScreenBinding
import com.example.mechat.utils.FirebaseUtils

class SplashScreen : AppCompatActivity()
{
    private lateinit var i : Intent
    private lateinit var binnding : SplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binnding = DataBindingUtil.setContentView(this, R.layout.splash_screen)
        animate()
        Handler().postDelayed({
            transferToNextActivity()
        }, 3000)
        print(" $FirebaseUtils.firebaseUser  user in your town ...")


    }

    private fun animate() {
        val mFade = Fade(Fade.OUT)
        TransitionManager.beginDelayedTransition(binnding.splach, mFade)
        val animator = ObjectAnimator.ofFloat(binnding.imageView7, View.ROTATION, -360f, 0f)
        animator.duration = 1000
        animator.start()
    }

    private fun transferToNextActivity() {
        if (FirebaseUtils.firebaseUser != null) {
            i = Intent(this, Home::class.java)
        } else {
            i = Intent(this, Login::class.java)
        }
        startActivity(i)
        finish()
    }
}