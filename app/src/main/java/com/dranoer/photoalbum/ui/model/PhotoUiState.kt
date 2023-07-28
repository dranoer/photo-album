package com.dranoer.photoalbum.ui.model

sealed class PhotoUiState {
    object Empty : PhotoUiState()
    object Loading : PhotoUiState()
    class Loaded(val data: List<PhotoUiModel>, val isRefreshing: Boolean = false) : PhotoUiState()
    class Error(val message: String) : PhotoUiState()
}