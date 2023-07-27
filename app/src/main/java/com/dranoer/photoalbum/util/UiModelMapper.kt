package com.dranoer.photoalbum.util

import com.dranoer.photoalbum.domain.model.AlbumItem
import com.dranoer.photoalbum.ui.model.AlbumUiModel
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
}