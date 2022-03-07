package com.example.mechat.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.mechat.R
import com.example.mechat.databinding.ActivityLoginBinding
import com.example.mechat.utils.Extensions.toast
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.utils.FirebaseUtils.firebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    lateinit var builder: AlertDialog.Builder
    lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        builder = AlertDialog.Builder(this)
        binding.button2.setOnClickListener {
            loadingDialog()
            firebaseAuth.signInWithEmailAndPassword(binding.username.text.toString(),
            binding.editTextTextPassword.text.toString())
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        stopLoadingDialog()
                        startActivity(Intent(this, WelecomActivity::class.java))
                        toast("signed in successfully")
                        finish()
                    } else {
                        toast("sign in failed")
                        stopLoadingDialog()
                    }
                }
        }
        binding.sighnUpOption.setOnClickListener {
            toast("created account successfully !")
            startActivity(Intent(this, SighUpActivity::class.java))
            finish()
        }

    }

    private fun stopLoadingDialog() {
        dialog.dismiss()
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
    private fun loadingDialog(){
        builder.setView(layoutInflater.inflate(R.layout.activity_loading_layout,null))
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.show()
    }
}