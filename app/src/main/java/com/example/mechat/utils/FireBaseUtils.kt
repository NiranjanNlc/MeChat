package com.example.mechat.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage


object FirebaseUtils {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
    val database = Firebase.database.getReferenceFromUrl("https://mechat-181d7-default-rtdb.firebaseio.com/")
    val storage = FirebaseStorage.getInstance()
}