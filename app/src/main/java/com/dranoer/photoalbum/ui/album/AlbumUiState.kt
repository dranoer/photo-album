package com.dranoer.photoalbum.ui.album

import com.dranoer.photoalbum.domain.model.AlbumItem

sealed class AlbumUiState {

    data class Loading(val isRefreshing: Boolean = false) : AlbumUiState()
    data class Success(val data: List<AlbumItem>, val isRefreshing: Boolean = false) : AlbumUiState()
    data class Error(val message: String, val isRefreshing: Boolean = false) : AlbumUiState()
}