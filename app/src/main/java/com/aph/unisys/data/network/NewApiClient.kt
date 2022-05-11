package com.aph.unisys.data.network

import com.aph.unisys.data.model.NewModel
import com.aph.unisys.data.network.responses.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewApiClient {
    @GET("top-headlines")
    suspend fun getAllNew(@Query("country") country:String,@Query("q") contentToSearch:String): Response<NewsResponse>
}
