package com.dranoer.photoalbum.domain

import com.dranoer.photoalbum.data.remote.WebService
import com.dranoer.photoalbum.domain.model.AlbumItem
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val webService: WebService,
) {

    suspend fun fetchAlbumList(): List<AlbumItem> {
        val response = webService.fetchAlbumList()
        return albumMapper(albumList = response)
    }
}