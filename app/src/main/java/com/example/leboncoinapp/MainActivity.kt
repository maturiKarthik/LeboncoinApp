package com.example.leboncoinapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.leboncoinapp.network.ApiRetrofitBuilder
import com.example.leboncoinapp.network.LebonCoinData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val lebonCoinApiInterface = ApiRetrofitBuilder.build()
            lebonCoinApiInterface.getAllProducts()
                .enqueue(object : retrofit2.Callback<List<LebonCoinData>> {
                    override fun onFailure(call: Call<List<LebonCoinData>>, t: Throwable) {
                        Log.e("Error", "Error To fetch Data")
                    }

                    override fun onResponse(
                        call: Call<List<LebonCoinData>>,
                        response: Response<List<LebonCoinData>>
                    ) {
                        val dataRecv = response.body()
                        for (index in 0 until dataRecv!!.size) {
                            Log.w("Body", "${dataRecv[index].id} -- ${dataRecv[index].albumId}")
                        }

                    }
                })
        }
    }
}


