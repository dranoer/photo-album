package com.dranoer.photoalbum.util

import com.dranoer.photoalbum.domain.model.AlbumItem
import com.dranoer.photoalbum.domain.model.PhotoItem
import com.dranoer.photoalbum.ui.model.AlbumUiModel
import com.dranoer.photoalbum.ui.model.PhotoUiModel
import javax.inject.Inject

class UiModelMapper @Inject constructor() {
    fun mapAlbums(albumList: List<AlbumItem>): List<AlbumUiModel> {
        return albumList.map { albumItem ->
            AlbumUiModel(
                userId = albumItem.id,
                id = albumItem.id,
                title = albumItem.title,
            )
        }
    }

    fun mapPhotos(photoList: List<PhotoItem>): List<PhotoUiModel> {
        return photoList.map { photo ->
            PhotoUiModel(
                albumId = photo.albumId,
                id = photo.id,
                title = photo.title,
                url = photo.url,
                thumbnailUrl = photo.thumbnailUrl,
            )
        }
    }
}