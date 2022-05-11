package com.aph.unisys.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.aph.unisys.data.database.entites.NewEntity
import com.aph.unisys.data.model.NewModel

data class New(val title: String?,val description: String?,val content: String?,val publishedAt: String?,val urlToImage:String?,val url:String?) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(content)
        parcel.writeString(publishedAt)
        parcel.writeString(urlToImage)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<New> {
        override fun createFromParcel(parcel: Parcel): New {
            return New(parcel)
        }

        override fun newArray(size: Int): Array<New?> {
            return arrayOfNulls(size)
        }
    }
}

fun NewModel.toDomain() = New(title, description, content, publishedAt, urlToImage, url)
fun NewEntity.toDomain() = New(title, description, content, publishedAt, urlToImage, url)