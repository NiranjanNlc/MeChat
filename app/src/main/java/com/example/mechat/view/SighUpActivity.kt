package com.example.mechat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sigh_up)
      //  binding = ActivitySighUpBinding.inflate(layoutInflater)
      //  setContentView(binding.root)
        viewModal  = ViewModalFactory().create(AuthenciationViewModal ::class.java)
        /*create a user*/
        binding.viewModal = viewModal
        binding.button2.setOnClickListener{
            Log.d(" butttom of sighnup  ", " pressed")
            signUp( )
        }

    }

    override fun onStart() {
        super.onStart()
//        val user: FirebaseUser? = firebaseAuth.currentUser
//        user?.let {
//            startActivity(Intent(this, WelecomActivity::class.java))
//            toast("welcome back")
//        }
    }

    private fun signUp()
    {
        viewModal.sighnUp()
        Log.i(" sucess authenciation" , viewModal.sighnUpStatus.value.toString())
        if(viewModal.sighnUpStatus.value == true)
        {
            toast("created account successfully under given condition !")
            startActivity(Intent(this, Login ::class.java))
            finish()
        }
    }
}