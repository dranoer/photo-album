package com.dranoer.photoalbum.util

sealed class Route(val route: String) {

    object Album : Route(Constant.ALBUM_SCREEN)
}