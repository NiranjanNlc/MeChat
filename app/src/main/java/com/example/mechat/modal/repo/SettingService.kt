package com.example.mechat.modal.repo

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mechat.modal.data.Users
import com.example.mechat.utils.FirebaseUtils
import kotlinx.coroutines.tasks.await

object SettingService {

    var profileImageUrl = MutableLiveData<String>()
    var user = MutableLiveData<Users>()
    private val uid =FirebaseUtils.firebaseUser?.uid

    fun saveImageFireBaseStorage(image: Intent?)
    {
        val  riversRef = FirebaseUtils.storage.reference.child("images/$uid")
        val uploadTask = image?.data?.let { riversRef.putFile(it) }
// Register observers to listen for when the download is done or if it fails
        if (uploadTask != null) {
            uploadTask.addOnFailureListener {
                Log.d(" success story ", it.message.toString())
                // binding.profilePic.setImageURI(list?.data)
                // Handle unsuccessful uploads
            }.addOnSuccessListener { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                    Log.d(" Unsucesss story ", " haha ")
                    profileImageUrl.postValue(it.toString())
                }
            }
        }
    }
    suspend fun getUserFromDb(uid: String){
        Log.i(" add user detail ","get user from db  .... ")

        return try{
            val data =FirebaseUtils.database.child("users/${uid}/").get().await()
            Log.i(" add user detail ","user $data ")
            Log.i(" data manipulated ",data.toString())
            user.value =data.getValue(Users ::class.java)
        }
        catch (e : Exception)
        {
            println( " eoor encountered during the operation " + e.message)
        }
    }

    suspend fun updateProfileImageUrl( profileImageUrl: String) {
        try{
            val data =   FirebaseUtils.database.child("/users/$uid/").child("profilePic").setValue(profileImageUrl).await()
            println(" dta base result $data.toString()")
        }
        catch (e : Exception)
        {
            println( " eoor encountered during the operation " + e.message)
        }

    }
}