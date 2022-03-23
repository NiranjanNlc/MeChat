package com.example.mechat.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage


object FirebaseUtils {
    var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var firebaseUser: FirebaseUser? = firebaseAuth.currentUser
    var database = Firebase.database.getReferenceFromUrl("https://mechat-181d7-default-rtdb.firebaseio.com/")
    var storage = FirebaseStorage.getInstance()
    var sender = firebaseUser?.uid
}