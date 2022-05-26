package com.example.myapplication.data.network

import okhttp3.Interceptor
import okhttp3.Response

class Interceptor constructor(private val tokenType: String, private val accessToken: String): Interceptor {

    override fun intercept(chain: okhttp3.Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("access", "$tokenType $accessToken")
            .build()
        return chain.proceed(request)
    }
}