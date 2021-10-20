package com.example.mechat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechat.R
import com.example.mechat.databinding.ActivitySighUpBinding
import com.example.mechat.modal.data.Users
import com.example.mechat.utils.Extensions.toast
import com.example.mechat.utils.FirebaseUtils.firebaseAuth
import com.example.mechat.viewmodal.AuthenciationViewModal
import com.example.mechat.viewmodal.ViewModalFactory
import com.google.firebase.auth.FirebaseUser

class SighUpActivity : AppCompatActivity()
{
    private lateinit var binding:ActivitySighUpBinding
    private lateinit var viewModal:AuthenciationViewModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySighUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModal  = ViewModalFactory().create(AuthenciationViewModal ::class.java)
        /*create a user*/

        binding.button2.setOnClickListener{
            signUp( )
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

    private fun signUp()
    {
        if(viewModal.sighnUp())
        {
            toast("created account successfully !")
            startActivity(Intent(this, WelecomActivity::class.java))
            finish()
        }
    }
}