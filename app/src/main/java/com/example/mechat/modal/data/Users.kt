package com.example.mechat.modal.data

import androidx.room.PrimaryKey
import java.util.*

data class Users(val profilePic:String ="" ,
            val userName:String="",
            val mail:String = "",
            val password : String ="hhh",
          //   val lastMessage:String =""
             @PrimaryKey(autoGenerate = true)
                 val userId:String = (Random().nextInt(9999910) + 1).toString()
)
{

}