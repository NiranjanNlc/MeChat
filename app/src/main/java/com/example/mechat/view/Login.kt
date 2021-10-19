package com.example.mechat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechat.R
import com.example.mechat.databinding.ActivityLoginBinding
import com.example.mechat.utils.Extensions.toast
import com.example.mechat.utils.FirebaseUtils

class Login : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }
}