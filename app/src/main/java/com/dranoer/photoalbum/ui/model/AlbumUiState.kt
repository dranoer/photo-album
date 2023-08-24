package com.dranoer.photoalbum.ui.model

sealed class AlbumUiState {
    object Empty : AlbumUiState()
    object Loading : AlbumUiState()
    data class Loaded(val data: List<AlbumUiModel>, val isRefreshing: Boolean = false) : AlbumUiState()
    data class Error(val message: String) : AlbumUiState()
}