package com.example.mechat.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechat.databinding.SampleRecieverBinding
import com.example.mechat.databinding.SampleSenderBinding
import com.example.mechat.modal.Message
import com.example.mechat.utils.FirebaseUtils

class DetailChatAdapter(context: Context , var messages :List<Message>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    val recieverViewType : Int = 2;
    val senderViewType : Int = 1;
    class ReceiverViewHolder(var binding: SampleRecieverBinding) : RecyclerView.ViewHolder(binding.root)
    {}
    class SenderViewHolder(var binding: SampleSenderBinding) : RecyclerView.ViewHolder(binding.root)
    {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType ==2)
        {
            val inflater = LayoutInflater.from(parent.context)
            val binding = SampleRecieverBinding.inflate(inflater)
            return ReceiverViewHolder(binding);
        }
        else
        {
            val inflater = LayoutInflater.from(parent.context)
            val binding = SampleSenderBinding.inflate(inflater)
            return SenderViewHolder(binding)
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
         if(holder.javaClass == SenderViewHolder::class.java)
         {
            //code for binding
         }
        else
         {
            //code for binding
         }
    }
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    override fun getItemViewType(position: Int): Int
    {
        if(messages[position].uid.equals(FirebaseUtils.firebaseUser?.uid))
            return senderViewType
        else
        return recieverViewType
    }
}