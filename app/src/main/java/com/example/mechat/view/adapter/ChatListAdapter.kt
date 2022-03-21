package com.example.mechat.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.mechat.databinding.ChatItemsBinding
import com.example.mechat.databinding.UserItemsBinding
import com.example.mechat.modal.data.Chats
import com.example.mechat.modal.data.Users
import com.example.mechat.view.adapter.ChatListAdapter.*

class ChatListAdapter constructor(val clickListener: OnChatClickListener ):
    ListAdapter<Chats, ChatListViewHolder>(COMPARATOR)
//   ListAdapter<Chats,ChatListAdapter.ChatListViewHolder>(COMPARATOR)
{

    interface OnChatClickListener
    {
        fun onClick(chats: Chats)
    }

      class ChatListViewHolder(var items: ChatItemsBinding,private val clickListener:OnChatClickListener): RecyclerView.ViewHolder(items.root) {

        fun bind(chatItems: Chats) {
         items.userChats= chatItems
        items.profileImage.load(chatItems.interlocutor?.profilePic)

        }
        companion object {
            fun from(parent: ViewGroup, clickListener: OnChatClickListener): ChatListViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ChatItemsBinding.inflate(inflater)
                return ChatListViewHolder(binding, clickListener)
            }
        }

        init {
            items.root.setOnClickListener {
               // clickListener.onClick(items.ch!!)
            }
        }
    }

    companion object COMPARATOR : DiffUtil.ItemCallback<Chats>()
    {
        override fun areItemsTheSame(oldItem: Chats, newItem: Chats): Boolean
        {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Chats, newItem: Chats): Boolean {
            return oldItem.equals(newItem)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder
    {
        println("On view create ")
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = ChatItemsBinding.inflate(inflater)
        return ChatListViewHolder.from(parent,clickListener)

    }
    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int)
    {
        val ChatItems = getItem(position)
       // println( " see thid " + ChatItems.profilePic.toString())
        holder.bind(ChatItems)
        holder.items.executePendingBindings()
    }
}