package com.example.mechat.modal.data

import androidx.room.PrimaryKey
import java.util.*
import java.io.Serializable

@kotlinx.serialization.Serializable
data class Users(val profilePic:String?=null ,
                 val userName:String?=null,
                 val mail:String?=null,
                 val password : String ="hhh",
    //   val lastMessage:String?=null
                 @PrimaryKey(autoGenerate = true)
                 var userId:String = (Random().nextInt(9999910) + 1).toString(),
                 var bio:String?=null
) : Serializable