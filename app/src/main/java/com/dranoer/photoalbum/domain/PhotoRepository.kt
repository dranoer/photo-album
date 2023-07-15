package com.dranoer.photoalbum.domain

import com.dranoer.photoalbum.data.remote.WebService
import com.dranoer.photoalbum.domain.model.AlbumItem
import com.dranoer.photoalbum.domain.model.PhotoItem
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val webService: WebService,
) {

    suspend fun fetchAlbums(): List<AlbumItem> {
        val response = webService.fetchAlbums()
        return albumMapper(albumList = response)
    }

    suspend fun fetchPhotos(id: Int): List<PhotoItem> {
        val response = webService.fetchPhotos(id = id)
        return photoMapper(photoList = response)
    }
}