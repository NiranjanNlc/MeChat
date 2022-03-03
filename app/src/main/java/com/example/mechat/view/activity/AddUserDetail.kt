package com.example.mechat.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mechat.R
import com.example.mechat.databinding.ActivityAddUserDetailBinding

class AddUserDetail : AppCompatActivity() {
    private lateinit var binding: ActivityAddUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddUserDetailBinding.inflate(layoutInflater)
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
        TODO("Not yet implemented")
    }

    private fun reverseBackToUserList() {
        TODO("Not yet implemented")
    }
}