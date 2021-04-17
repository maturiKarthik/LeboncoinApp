# LeboncoinApp

This App is about Fun

## RetorFit Library {Retrofit_Version = "2.9.0"}

 implementation "com.squareup.retrofit2:retrofit:$Retrofit_Version"
 implementation "com.squareup.retrofit2:converter-gson:$Retrofit_Version"

 The Moduel network holds the complete code of retrofit of implementation.

 Important Code to see:

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