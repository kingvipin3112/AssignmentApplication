package com.example.myapplication.data.network

import com.example.myapplication.data.ResponseData
import retrofit2.http.GET
import retrofit2.Response

interface ApiService {
    companion object {
        const val BASE_URL = ""
    }

    @GET
    suspend fun getDetails() : Response<ResponseData>

}