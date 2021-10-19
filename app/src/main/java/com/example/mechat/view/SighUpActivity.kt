package com.example.mechat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechat.R
import com.example.mechat.databinding.ActivitySighUpBinding
import com.example.mechat.utils.Extensions.toast
import com.example.mechat.utils.FirebaseUtils.firebaseAuth
import com.google.firebase.auth.FirebaseUser

class SighUpActivity : AppCompatActivity()
{
    private lateinit var binding:ActivitySighUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySighUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button2.setOnClickListener{
            signUp( binding.editTextTextPersonName.text.toString(),
                    binding.editTextTextPassword.text.toString())
        }

    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = firebaseAuth.currentUser
        user?.let {
            startActivity(Intent(this, WelecomActivity::class.java))
            toast("welcome back")
        }
    }

    private fun signUp(userEmail: String, userPassword: String) {

        /*create a user*/
        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    toast("created account successfully !")
                    startActivity(Intent(this, WelecomActivity::class.java))
                    finish()
                } else {
                    toast("failed to Authenticate !")
                }

            }
    }
}