package com.aph.unisys.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
Data class for store the type new data.
Contains:
    -Tile
    -Description (Teaser text)
    -Content(Large text)
    -publishedAt(Text)
    -urlToImage
    -url
 */
data class NewModel(
    @SerializedName("title")val title: String?,
    @SerializedName("description")val description: String?,
    @SerializedName("content")val content: String?,
    @SerializedName("publishedAt")val publishedAt: String?,
    @SerializedName("urlToImage")val urlToImage:String?,
    @SerializedName("url")val url:String?)



