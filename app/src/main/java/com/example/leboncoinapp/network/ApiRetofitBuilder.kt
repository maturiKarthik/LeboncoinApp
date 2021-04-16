package com.example.leboncoinapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  ApiRetrofitBuilder {
    fun build():LeboncoinApiInterface{
        val retrofit:Retrofit = Retrofit.Builder().baseUrl("https://static.leboncoin.fr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(LeboncoinApiInterface::class.java)
    }
}