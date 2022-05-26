package com.example.myapplication.di

import com.example.myapplication.data.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .run {
                add(KotlinJsonAdapterFactory())
                build()
            }

    @Provides
    @Singleton
    fun provideApiService(moshi: Moshi, okHttpClient: OkHttpClient): ApiService = Retrofit
        .Builder()
        .run {
            baseUrl(ApiService.BASE_URL)
            addConverterFactory(MoshiConverterFactory.create(moshi))
            client(okHttpClient)
            build()
        }.create(ApiService::class.java)
}