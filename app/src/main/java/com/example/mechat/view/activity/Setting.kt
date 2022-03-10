package com.example.mechat.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import coil.load
import com.example.mechat.R
import com.example.mechat.utils.Extensions.toast
import com.example.mechat.viewmodal.SettingViewModal
import com.example.mechat.viewmodal.ViewModalFactory

class Setting : AppCompatActivity() {
    private lateinit var binding:  com.example.mechat.databinding.ActivityAddUserDetailBinding
    private lateinit var viewModal: SettingViewModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_user_detail)
        initialiseViewModal()
        binding.backArrow.setOnClickListener {
            reverseBackToUserList()
        }
        binding.addImage.setOnClickListener {
            updateProfileImage()
        }
        binding.saveInfo.setOnClickListener {
            // viewModal.saveInfo()
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModal.user.observe(this
        ) {
            println("  user is  ${it.userName}")
            binding.user1 = it
            binding.profilePic.load(it.profilePic?.toUri()?.buildUpon()?.scheme("https")?.build())
        }
        viewModal.profileImageUrl.observe(this
        ) {
            Log.i(" add user detail ", "profile Image url updtaed ")
            Log.i(" add user detail ", "$it ")
            updateUserInfo(it)
            loadProfilePic(it)
        }
    }

    private fun initialiseViewModal() {
        Log.i(" add user detail ","added ")
        viewModal  = ViewModalFactory().create(SettingViewModal ::class.java)
        binding.viewmodal = viewModal
        val user = viewModal.user.value
        Log.i(" detail from viewmoal  ",user.toString())
        binding.user1= user
        if (user != null) {
            binding.profilePic.load(user.profilePic?.toUri()?.buildUpon()?.scheme("https")?.build())
        }

    }

    private fun updateProfileImage() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        previewRequest.launch(intent)
    }

    private fun reverseBackToUserList() {
        startActivity(Intent(this, Home::class.java))
        toast("Logged out sucess fully ")
        finish()
    }

    private val previewRequest =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val list = it.data
                // sendDataToFireBaseStorage(list)
                viewModal.sendImageToFireBase(list)
            }
        }
    private fun loadProfilePic(riversRef: String) {
        println(" Will be implemented thrpough picaso")
        Log.d(" url download ", riversRef)
        val imgUri = riversRef.toUri().buildUpon().scheme("https").build()
        binding.profilePic.load(imgUri)
    }

    private fun updateUserInfo(profileImageUrl: String) {
        viewModal.updateUserInfo(profileImageUrl)
    }
}