package com.dranoer.photoalbum.ui.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dranoer.photoalbum.domain.PhotoRepository
import com.dranoer.photoalbum.domain.model.PhotoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val repository: PhotoRepository,
) : ViewModel() {

    private val _photoState = MutableStateFlow<PhotoUiState>(PhotoUiState.Empty)
    val photoState: StateFlow<PhotoUiState> = _photoState.asStateFlow()

    fun fetchPhotos(albumId: Int) {
        _photoState.value = PhotoUiState.Loading
        viewModelScope.launch {
            try {
                val result = repository.fetchPhotos(id = albumId)
                _photoState.value = PhotoUiState.Loaded(data = result, isRefreshing = false)
            } catch (e: Exception) {
                _photoState.value = PhotoUiState.Error(message = e.message ?: "Unknown error")
            }
        }
    }

    fun getPhotoDetail(photoId: Int): PhotoItem? {
        return when (val state = _photoState.value) {
            is PhotoUiState.Loaded -> {
                state.data.let { photos ->
                    photos.find { it.id == photoId }
                }
            }

            else -> null
        }
    }
}