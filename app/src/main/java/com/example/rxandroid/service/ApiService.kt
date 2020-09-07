package com.example.rxandroid.service

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
    private fun createOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addNetworkInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request = chain.request()
                    val url = request.url.newBuilder().addQueryParameter("api_key", API_KEY)
                        .build()
                    Log.d("Tim",url.toUrl().toString())
                    request = request.newBuilder().url(url).build()
                    return chain.proceed(request)
                }
            })
            .addInterceptor(logger)
            .connectTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    fun getClient(): TheMovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMovieApi::class.java)
    }
}
