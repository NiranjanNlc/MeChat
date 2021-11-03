package com.example.mechat.view.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mechat.modal.Message

class DetailChatAdapter(context: Context , messages :List<Message>)
{
    val recieverViewType : Int = 2;
    val senderViewType : Int = 1;
    inner class ReceiverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {}
    inner class SenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {}

}