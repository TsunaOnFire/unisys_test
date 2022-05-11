package com.aph.unisys.data.database.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aph.unisys.domain.model.New

@Entity(tableName = "new_table")
data class NewEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id: Int =0,
    @ColumnInfo(name = "title")val title: String?,
    @ColumnInfo(name = "description")val description: String?,
    @ColumnInfo(name = "content")val content: String?,
    @ColumnInfo(name = "publishedAt")val publishedAt: String?,
    @ColumnInfo(name = "urlToImage")val urlToImage:String?,
    @ColumnInfo(name = "url")val url:String?
)

fun New.toDatabase() = NewEntity(title = title, description = description, content = content, publishedAt = publishedAt, urlToImage = urlToImage, url = url)