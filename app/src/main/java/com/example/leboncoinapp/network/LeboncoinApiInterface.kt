package com.example.leboncoinapp.network


import retrofit2.http.GET

interface LeboncoinApiInterface {

    @GET("/img/shared/technical-test.json")
    fun getAllProducts(): retrofit2.Call<List<LebonCoinData>>
}
