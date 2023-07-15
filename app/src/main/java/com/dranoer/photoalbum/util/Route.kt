package com.dranoer.photoalbum.util

import com.dranoer.photoalbum.util.Constant.ALBUM_ID
import com.dranoer.photoalbum.util.Constant.ALBUM_SCREEN
import com.dranoer.photoalbum.util.Constant.PHOTO_SCREEN
import com.dranoer.photoalbum.util.Constant.DETAIL_SCREEN

sealed class Route(val route: String) {

    object Album : Route(ALBUM_SCREEN)
    object Photo : Route("$PHOTO_SCREEN/{$ALBUM_ID}") {
        fun createRoute(id: Int) = "$PHOTO_SCREEN/$id"
    }

    object Detail : Route(DETAIL_SCREEN)
}