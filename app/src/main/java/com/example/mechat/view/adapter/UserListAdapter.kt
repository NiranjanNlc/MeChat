package com.example.mechat.view.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mechat.databinding.UserItemsBinding
import com.example.mechat.modal.data.Users
import com.example.mechat.view.ChatDetailActivity
import com.example.mechat.view.WelecomActivity

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class UserListAdapter(
    val context: Context)
: ListAdapter<Users, UserListAdapter.UserListViewHolderr>(COMPARATOR)

{

    inner class UserListViewHolderr(var items: UserItemsBinding): RecyclerView.ViewHolder(items.root)
    {
//        init {
//            items.root.setOnClickListener{
////                items.userItems?.userName?.let { it1 -> itemClickListener.onItemClick(it1) }
//            }
//        }
        fun bind(user: Users)
        {
            Log.i(" binding " , user.toString())
            items.userItems = user

        }
    }
    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Users>() {
            override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
                println(" item same ")
                return oldItem == newItem;
            }

            override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
                print(" Content same ")
                return oldItem.userId.equals(newItem.userId)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolderr {
        println("On view create ")
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserItemsBinding.inflate(inflater)
        return UserListViewHolderr(binding)

    }

    override fun onBindViewHolder(holder: UserListViewHolderr, position: Int)
    {
        val user = getItem(position)
        println( " see thid " + user)
        holder.bind(user)
        holder.items.executePendingBindings()
        holder.itemView.setOnClickListener {
            var i = Intent(context, ChatDetailActivity ::class.java)
            i.putExtra("userId",user.userId)
            i.putExtra("userName",user.userName)
            i.putExtra("userProfile",user.profilePic)
            context.startActivity(i)
        }
    }
}