package com.dranoer.photoalbum.domain

import com.dranoer.photoalbum.data.remote.model.AlbumModel
import com.dranoer.photoalbum.data.remote.model.PhotoModel
import com.dranoer.photoalbum.domain.model.AlbumItem
import com.dranoer.photoalbum.domain.model.PhotoItem

fun albumMapper(albumList: List<AlbumModel>): List<AlbumItem> {
    return albumList.map { album ->
        AlbumItem(userId = album.userId, id = album.id, title = album.title)
    }
}

fun photoMapper(photoList: List<PhotoModel>): List<PhotoItem> {
    return photoList.map { photo ->
        PhotoItem(
            albumId = photo.albumId,
            id = photo.id,
            title = photo.title,
            url = photo.url,
            thumbnailUrl = photo.thumbnailUrl,
        )
    }
}