package com.dranoer.photoalbum.domain

import com.dranoer.photoalbum.data.remote.model.AlbumModel
import com.dranoer.photoalbum.domain.model.AlbumItem

fun albumMapper(albumList: List<AlbumModel>): List<AlbumItem> {

    return albumList.map { album ->
        AlbumItem(userId = album.userId, id = album.id, title = album.title)
    }
}