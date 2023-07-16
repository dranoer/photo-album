package com.dranoer.photoalbum.ui.album

import com.dranoer.photoalbum.domain.model.AlbumItem

sealed class AlbumUiState {
    object Empty : AlbumUiState()
    object Loading : AlbumUiState()
    data class Loaded(val data: List<AlbumItem>, val isRefreshing: Boolean = false) : AlbumUiState()
    data class Error(val message: String) : AlbumUiState()
}