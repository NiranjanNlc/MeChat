package com.example.mechat.modal

class Message
{
    constructor() //empty for firebase
    constructor(messageText: String){
        text = messageText
    }
    var text: String? = null
    var timestamp: Long = System.currentTimeMillis()
    var senderName: String?= null
    var uid: String?= null
}