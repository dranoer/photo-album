package com.dranoer.photoalbum

sealed class Route(val route: String) {

    object Album : Route(Constant.ALBUM_SCREEN)
}