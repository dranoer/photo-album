package com.dranoer.photoalbum.domain.model

data class PhotoItem(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
)