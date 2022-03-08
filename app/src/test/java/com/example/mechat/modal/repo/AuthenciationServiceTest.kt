package com.example.mechat.modal.repo

import com.example.mechat.modal.data.Users
import com.example.mechat.utils.FirebaseUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class AuthenciationServiceTest {


    private val user= Users(userName ="niranjan",mail = "nloj@gmail.com",password ="3456")

    @Test
    fun sighnUpUser()
    {
       // AuthenciationService.sighnUpUser(user)
        val data = FirebaseUtils.database.child("users/${user.userId}").get().result to Users::class.java
        assertEquals(data,user)
    }
}