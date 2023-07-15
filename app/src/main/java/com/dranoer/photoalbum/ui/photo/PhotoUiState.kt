package com.dranoer.photoalbum.ui.photo

import com.dranoer.photoalbum.domain.model.PhotoItem

sealed class PhotoUiState {

    data class Loading(val isRefreshing: Boolean = false) : PhotoUiState()
    data class Success(val data: List<PhotoItem>, val isRefreshing: Boolean = false) : PhotoUiState()
    data class Error(val message: String, val isRefreshing: Boolean = false) : PhotoUiState()
}