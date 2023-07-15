package com.dranoer.photoalbum.data.remote

import com.dranoer.photoalbum.data.remote.model.AlbumModel
import com.dranoer.photoalbum.data.remote.model.PhotoModel
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {

    @GET("albums")
    suspend fun fetchAlbums(): List<AlbumModel>

    @GET("albums/{albumId}/photos")
    suspend fun fetchPhotos(
        @Path("albumId") id: Int
    ): List<PhotoModel>
}