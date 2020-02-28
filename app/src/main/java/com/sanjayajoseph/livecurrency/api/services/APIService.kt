package com.sanjayajoseph.livecurrency.api.services

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/


class APIService(baseUrl: String, context: Context) {
    private val mContext = context
    private val okHttpClient: OkHttpClient
        get() {
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val httpUrl = original.url

                val newHttpUrl = httpUrl.newBuilder()
                    .build()

                val requestBuilder = original.newBuilder().url(newHttpUrl)
                    .addHeader("Content-Type", "application/json")

                val request = requestBuilder.build()

                chain.proceed(request)
            }
            val logging = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            httpClient.addInterceptor(logging).build()
            httpClient.addInterceptor(ChuckInterceptor(mContext))

            return httpClient.build()
        }

    internal val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

}