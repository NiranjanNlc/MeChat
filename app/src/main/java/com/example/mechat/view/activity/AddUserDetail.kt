package com.example.mechat.view.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mechat.databinding.ActivityAddUserDetailBinding
import com.example.mechat.utils.Extensions.toast
import com.example.mechat.utils.FirebaseUtils
import java.io.File

class AddUserDetail : AppCompatActivity() {
    private lateinit var binding: ActivityAddUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backArrow.setOnClickListener {
            reverseBackToUserList()
        }
        binding.addImage.setOnClickListener {
            updateProfileImage()
        }
        binding.saveInfo.setOnClickListener {
            saveInfo()
        }
    }

    private fun saveInfo() {
        TODO("Not yet implemented")
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

    val previewRequest =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val list = it.data
                 binding.profilePic.setImageURI(list?.data)
                 sendDataToFireBaseStorage(list)
            }
        }

    private fun sendDataToFireBaseStorage(data: Intent?)
    {
        var file = data?.toUri(0)
        val riversRef = FirebaseUtils.storage.reference.child("images/${file.toString()}")
        val uploadTask = data?.data?.let { riversRef.putFile(it) }

// Register observers to listen for when the download is done or if it fails
        if (uploadTask != null) {
            uploadTask.addOnFailureListener {
                println(" success story ")
                // Handle unsuccessful uploads
            }.addOnSuccessListener { taskSnapshot ->
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                // ...
                println(" Unsucesss story ")

            }
        }

    }
}