package com.aph.unisys.core.di

import com.aph.unisys.data.network.HeaderInterceptor
import com.aph.unisys.data.network.NewApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private fun getClient(): OkHttpClient = OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
    }

    @Singleton
    @Provides
    fun provideNewApiClient(retrofit: Retrofit):NewApiClient{
        return retrofit.create(NewApiClient::class.java)
    }
}