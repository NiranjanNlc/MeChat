package com.example.mechat.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechat.databinding.SampleRecieverBinding
import com.example.mechat.databinding.SampleSenderBinding
import com.example.mechat.modal.data.ChatMessage
import com.example.mechat.utils.FirebaseUtils

class DetailChatAdapter(context: Context, private val messages: List<ChatMessage>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private val recieverViewType : Int = 2
    private val senderViewType : Int = 1

    class ReceiverViewHolder(var binding: SampleRecieverBinding) : RecyclerView.ViewHolder(binding.root)
    class SenderViewHolder(var binding: SampleSenderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==2)
        {
            val inflater = LayoutInflater.from(parent.context)
            val binding = SampleRecieverBinding.inflate(inflater)
            return ReceiverViewHolder(binding)
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
        val mesge = messages[position]
        if(holder.javaClass == SenderViewHolder::class.java)
        {
            ( holder as SenderViewHolder).binding.textView.text = mesge.text
        }
        else
        {
            ( holder as ReceiverViewHolder).binding.textView.text = mesge.text
        }
    }
    override fun getItemCount(): Int
    {
        Log.i(" message size ", messages.size.toString())
        return messages.size
    }
    override fun getItemViewType(position: Int): Int
    {
        Log.i(" types of view here ",FirebaseUtils.firebaseUser.toString())
        if(messages[position].senderId.equals(FirebaseUtils.firebaseUser?.uid))
            return senderViewType
        else
            return recieverViewType
    }
}