package com.moneybox.minimb.core.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Networking {
    fun createClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(NoAuthenticationInterceptor())
            .build()
    }

    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}