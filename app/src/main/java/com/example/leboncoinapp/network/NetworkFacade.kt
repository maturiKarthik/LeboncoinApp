package com.example.leboncoinapp.network

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

// Class to call for all Network Call
object NetworkFacade {

    fun connect(networkListener: NetworkListener, context: Context) {
        GlobalScope.launch {
            val lebonCoinApiInterface = ApiRetrofitBuilder.build(context)
            lebonCoinApiInterface.getAllProducts().apply {
                enqueue(object : retrofit2.Callback<List<LebonCoinData>> {
                    override fun onFailure(call: Call<List<LebonCoinData>>, t: Throwable) {
                        if (t is NoConnectivityException) networkListener.onConnectionFail(t.message.toString())
                    }

                    override fun onResponse(
                            call: Call<List<LebonCoinData>>,
                            response: Response<List<LebonCoinData>>
                    ) {
                        networkListener.onResponseBodyReceive(response.body())
                    }
                })
            }
        }
    }
}

//Listener to all network
interface NetworkListener {
    fun onConnectionFail(message: String)
    fun onResponseBodyReceive(responseBody: List<LebonCoinData>?)
}