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
import com.example.mechat.viewmodal.UserDetailViewModal
import com.example.mechat.viewmodal.ViewModalFactory

class AddUserDetail : AppCompatActivity() {
    private lateinit var binding:  com.example.mechat.databinding.ActivityAddUserDetailBinding
    private lateinit var viewModal: UserDetailViewModal
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
        viewModal  = ViewModalFactory().create(UserDetailViewModal ::class.java)
        binding.viewmodal = viewModal
        binding.user1= viewModal.user.value
    }

    private fun updateProfileImage() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        previewRequest.launch(intent)
    }

    private fun reverseBackToUserList() {
        startActivity(Intent(this, WelecomActivity::class.java))
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