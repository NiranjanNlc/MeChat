package com.example.mechat.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import coil.load
import com.example.mechat.R
import com.example.mechat.databinding.ActivityAddUserDetailBinding
import com.example.mechat.utils.Extensions.toast
import com.example.mechat.utils.FirebaseUtils
import com.example.mechat.viewmodal.ChatDetailViewModal
import com.example.mechat.viewmodal.UserDetailViewModal
import com.example.mechat.viewmodal.ViewModalFactory
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class AddUserDetail : AppCompatActivity() {
    private lateinit var binding: ActivityAddUserDetailBinding
    private lateinit var riversRef: StorageReference
    private lateinit var profileImageUrl: String
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
            saveInfo()
        }
        observeViewModel()
    }

    private fun observeViewModel() {
         viewModal.user.observe(this,
             {
                 println("  user is  ${it.userName}")
                 binding.user1 = it
             })
    }

    private fun initialiseViewModal() {
        Log.i(" add user detail ","added ")
        viewModal  = ViewModalFactory().create(UserDetailViewModal ::class.java)
        binding.viewmodal = viewModal
    }


    private fun saveInfo() {
         println(" Now implemented ")
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
                 sendDataToFireBaseStorage(list)
            }
        }

    private fun sendDataToFireBaseStorage(data: Intent?)
    {
        var file = data?.toUri(0)
        riversRef = FirebaseUtils.storage.reference.child("images/${
            FirebaseUtils.firebaseUser?.uid}")
        val uploadTask = data?.data?.let { riversRef.putFile(it) }

// Register observers to listen for when the download is done or if it fails
        if (uploadTask != null) {
            uploadTask.addOnFailureListener {
                Log.d(" success story ", it.message.toString())
                // binding.profilePic.setImageURI(list?.data)
                // Handle unsuccessful uploads
            }.addOnSuccessListener ( OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                    Log.d(" Unsucesss story "," haha ")
                    profileImageUrl = it.toString()
                    loadProfilePic(profileImageUrl)
                }
            })
            }
        }
    private fun loadProfilePic(riversRef: String) {
         println(" Will be implemented thrpough picaso")
        Log.d(" url download ", profileImageUrl)
     //   updateUserInfo(profileImageUrl)
        val imgUri = profileImageUrl.toUri().buildUpon().scheme("https").build()
        binding.profilePic.load(imgUri)
    }

    private fun updateUserInfo(profileImageUrl: String) {

    }
}