package com.example.leboncoinapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.leboncoinapp.UI.CustomToast
import com.example.leboncoinapp.network.LebonCoinData
import com.example.leboncoinapp.network.NetworkFacade
import com.example.leboncoinapp.network.NetworkListener

class MainActivity : AppCompatActivity(), NetworkListener {

    private lateinit var progressBar: ProgressBar
    private lateinit var stringMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        NetworkFacade.connect(this, context = applicationContext)
    }

    private fun initView() {
        progressBar = findViewById(R.id.progress_circular)
        stringMessage = findViewById(R.id.text_message)
    }


    // NetworkListener Methods
    override fun onConnectionFail(message: String) {
        stringMessage.text = message
        CustomToast.display(this, message)
        progressBar.visibility = View.GONE
    }

    override fun onResponseBodyReceive(responseBody: List<LebonCoinData>?) {
        stringMessage.visibility = View.GONE
        CustomToast.display(this, "Sucess")
        progressBar.visibility = View.GONE
        for (index in responseBody!!.indices) {
            Log.w("NETWORK", "${responseBody[index].id} -- ${responseBody[index].albumId} -- ${responseBody[index].title}")
        }
    }
}


