package com.aph.unisys.data.network.responses

import com.aph.unisys.data.model.NewModel
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status") var status: String,
    @SerializedName("totalResults") var totalResults: Int,
    @SerializedName("articles") var articles: List<NewModel>?,
)