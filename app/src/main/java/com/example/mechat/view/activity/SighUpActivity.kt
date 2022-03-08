package com.example.mechat.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.mechat.R
import com.example.mechat.databinding.ActivitySighUpBinding
import com.example.mechat.utils.Extensions.toast
import com.example.mechat.viewmodal.AuthenciationViewModal
import com.example.mechat.viewmodal.ViewModalFactory

class SighUpActivity : AppCompatActivity()
{
    private lateinit var binding:ActivitySighUpBinding
    private lateinit var viewModal:AuthenciationViewModal
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sigh_up)
        builder = AlertDialog.Builder(this)
        viewModal  = ViewModalFactory().create(AuthenciationViewModal ::class.java)
        /*create a user*/
        binding.viewModal = viewModal
        binding.button2.setOnClickListener{
            loadingDialog()
            Log.d(" butttom of sighnup  ", " pressed")
            viewModal.sighnUp()
        }
        viewModal.firebaseUser.observe(this) {
            if (it != null) {
                viewModal.logOut()
                startActivity(Intent(this, Login::class.java))
                toast("signed in successfully")
                finish()
            }
        }
    }
    private fun loadingDialog(){
        builder.setView(layoutInflater.inflate(R.layout.activity_loading_layout,null))
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.show()
    }
}