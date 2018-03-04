package com.ottoboni.comicbook.data.source.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by caoj on 03/03/18.
 */
class ServiceClient {

    val BASE_URL = "https://hqtracker.herokuapp.com/"

    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient())
                .build()
    }

    fun getApiClient() = retrofit.create(ApiRequest::class.java)
}