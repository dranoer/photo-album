package com.dranoer.photoalbum.ui.photo

import com.dranoer.photoalbum.domain.model.PhotoItem

sealed class PhotoUiState {
    object Empty : PhotoUiState()
    object Loading : PhotoUiState()
    class Loaded(val data: List<PhotoItem>, val isRefreshing: Boolean) : PhotoUiState()
    class Error(val message: String) : PhotoUiState()
}