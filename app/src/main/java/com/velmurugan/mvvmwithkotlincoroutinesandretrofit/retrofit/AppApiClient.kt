package com.velmurugan.mvvmwithkotlincoroutinesandretrofit.retrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

const val REQUEST_TIMEOUT: Long = 25L


fun createAppApiClient(
    baseUrl: String
): Retrofit = retrofitClient(
    baseUrl
)

private fun retrofitClient(
    baseUrl: String
): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()







