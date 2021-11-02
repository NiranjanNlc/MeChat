package com.example.mechat.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mechat.databinding.UserItemsBinding
import com.example.mechat.modal.data.Users
import com.example.mechat.view.adapter.ChatListAdapter.*

class ChatListAdapter constructor(val context : Context ):
  ListAdapter<Users, UserListViewHolder>(COMPARATOR)
//   ListAdapter<Users,ChatListAdapter.UserListViewHolder>(COMPARATOR)
{

    inner  class UserListViewHolder(var items: UserItemsBinding): RecyclerView.ViewHolder(items.root) {

        fun bind(userItems: Users) {
            items.userItems= userItems
        }

    }

     companion object COMPARATOR : DiffUtil.ItemCallback<Users>()
    {
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean
        {
            return oldItem.profilePic == newItem.profilePic
        }

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
             return oldItem.equals(newItem)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder
    {
        println("On view create ")
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserItemsBinding.inflate(inflater)
        return UserListViewHolder(binding)

    }
    override fun onBindViewHolder(holder: UserListViewHolder, position: Int)
    {
        val userItems = getItem(position)
        println( " see thid " + userItems.profilePic.toString())
        holder.bind(userItems)
        Glide.with(context).load(userItems.profilePic).into(holder.items.profileImage)
        holder.items.executePendingBindings()
    }
}