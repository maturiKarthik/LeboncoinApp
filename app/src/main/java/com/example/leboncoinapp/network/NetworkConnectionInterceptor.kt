package com.example.leboncoinapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * This class handles the network connection before every
 * api call
 */

class NetworkConnectionInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!checkInternetOnDevice()) throw NoConnectivityException()

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun checkInternetOnDevice(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        Log.d("NETWORK", "Is DEVICE Connected $isConnected")
        return isConnected
    }
}

//Custom Exception
class NoConnectivityException : IOException() {
    override val message: String?
        get() = "No internet Connection"
}