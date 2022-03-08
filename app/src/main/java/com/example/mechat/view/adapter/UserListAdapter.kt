package com.example.mechat.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mechat.databinding.UserItemsBinding
import com.example.mechat.modal.data.Users


class UserListAdapter(private var clickListener: OnUserClickListener)
    : ListAdapter<Users, UserListAdapter.UserListViewHolderr>(COMPARATOR)

{
    interface OnUserClickListener
    {
        fun onClick(user: Users)
    }

    class UserListViewHolderr(var items: UserItemsBinding,private val clickListener: OnUserClickListener): RecyclerView.ViewHolder(items.root)
    {
        init {
            items.root.setOnClickListener {
                clickListener.onClick(items.userItems!!)
            }
        }

        fun bind(user: Users)
        {
            Log.i(" binding " , user.toString())
            items.userItems = user

        }
        companion object {
            fun from(parent: ViewGroup, clickListener: OnUserClickListener): UserListViewHolderr {
                val inflater = LayoutInflater.from(parent.context)
                val binding = UserItemsBinding.inflate(inflater)
                return UserListViewHolderr(binding,clickListener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolderr {
        println("On view create ")
        return UserListViewHolderr.from(parent,clickListener)

    }

    override fun onBindViewHolder(holder: UserListViewHolderr, position: Int)
    {
        val user = getItem(position)
        println(" see thid $user")
        holder.bind(user)
        holder.items.executePendingBindings()
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Users>() {
            override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
                println(" item same ")
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
                print(" Content same ")
                return oldItem.userId.equals(newItem.userId)
            }
        }

    }
}