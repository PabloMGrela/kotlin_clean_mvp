package com.grela.remote_datasource

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network() : KoinComponent {
    fun <T> provideApi(
        baseUrl: String,
        clazz: Class<T>
    ): T =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(clazz)

}