package com.dranoer.photoalbum.data.remote

import com.dranoer.photoalbum.data.remote.model.AlbumModel
import retrofit2.http.GET

interface WebService {

    @GET("albums")
    suspend fun fetchAlbumList(): List<AlbumModel>
}