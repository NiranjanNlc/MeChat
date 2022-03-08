package com.example.mechat.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mechat.R

class ChatListFragment : Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    {
        Log.i(" in the ", " chat list fragments ,,,,,,,,,")
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }
}


