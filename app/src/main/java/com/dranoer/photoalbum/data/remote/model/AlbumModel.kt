package com.dranoer.photoalbum.data.remote.model

import com.google.gson.annotations.SerializedName

data class AlbumModel(

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,
)