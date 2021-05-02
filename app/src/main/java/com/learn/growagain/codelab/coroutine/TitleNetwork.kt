package com.learn.growagain.codelab.coroutine

import com.learn.growagain.codelab.coroutine.util.SkipNetworkInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private val service: TitleNetwork by lazy {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(SkipNetworkInterceptor())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    retrofit.create(TitleNetwork::class.java)
}

fun getTitleService() = service

interface TitleNetwork {

    @GET("next_title.json")
    fun fetchNextTitle(): Call<String>
}