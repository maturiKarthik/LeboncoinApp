package com.example.leboncoinapp.UI

import android.content.Context
import android.widget.Toast

object CustomToast{
    fun display(context:Context,message: String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}