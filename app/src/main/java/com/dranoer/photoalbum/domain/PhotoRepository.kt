package com.dranoer.photoalbum.domain

import com.dranoer.photoalbum.data.remote.WebService
import com.dranoer.photoalbum.util.exception.toAppException
import com.dranoer.photoalbum.domain.model.AlbumItem
import com.dranoer.photoalbum.domain.model.PhotoItem
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val webService: WebService,
    private val mapper: PhotoMapper,
) {

    suspend fun fetchAlbums(): List<AlbumItem> {
        try {
            val response = webService.fetchAlbums()
            return mapper.mapAlbums(albumList = response)
        } catch (e: Exception) {
            throw e.toAppException()
        }
    }

    suspend fun fetchPhotos(id: Int): List<PhotoItem> {
        try {
            val response = webService.fetchPhotos(id = id)
            return mapper.mapPhotos(photoList = response)
        } catch (e: Exception) {
            throw e.toAppException()
        }
    }
}