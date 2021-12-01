package com.example.mechat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechat.R
import com.example.mechat.databinding.ActivityLoginBinding
import com.example.mechat.utils.Extensions.toast
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.utils.FirebaseUtils.firebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (FirebaseUtils.firebaseUser != null)
//        {
//            startActivity(Intent(this, WelecomActivity::class.java))
//            finish()
//        }
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button2.setOnClickListener {
            FirebaseUtils.firebaseAuth.signInWithEmailAndPassword(binding.username.text.toString(),
            binding.editTextTextPassword.text.toString())
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        startActivity(Intent(this, WelecomActivity::class.java))
                        toast("signed in successfully")
                        finish()
                    } else {
                        toast("sign in failed")
                    }
                }
        }
        binding.sighnUpOption.setOnClickListener {
            toast("created account successfully !")
            startActivity(Intent(this, SighUpActivity::class.java))
            finish()
        }

    }
    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = firebaseAuth.currentUser
        user?.let {
            startActivity(Intent(this, WelecomActivity::class.java))
            toast("welcome back")
            finish()
        }
    }
}