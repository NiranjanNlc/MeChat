package com.example.mechat.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechat.modal.Message

class DetailChatAdapter(context: Context , messages :List<Message>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    val recieverViewType : Int = 2;
    val senderViewType : Int = 1;
    class ReceiverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {}
    class SenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}