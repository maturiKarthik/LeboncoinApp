package com.example.leboncoinapp.network

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object  ApiRetrofitBuilder {
    fun build(context: Context):LeboncoinApiInterface{
        //Adding network interceptor to intercept no internet error
        val okHttpClient = OkHttpClient.Builder().addInterceptor(NetworkConnectionInterceptor(context)).build()

        val retrofit:Retrofit = Retrofit.Builder().baseUrl("https://static.leboncoin.fr/").client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(LeboncoinApiInterface::class.java)
    }
}