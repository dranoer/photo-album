package com.dranoer.photoalbum.data.remote.model

import com.google.gson.annotations.SerializedName

data class PhotoModel(

    @SerializedName("albumId")
    val albumId: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
)