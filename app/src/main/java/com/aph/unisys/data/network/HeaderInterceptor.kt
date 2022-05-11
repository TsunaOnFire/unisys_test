package com.aph.unisys.data.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("x-api-key","a1b593dc04ff492a8a416d1abc5f1cc7")
            .build()

        return chain.proceed(request)
    }
}