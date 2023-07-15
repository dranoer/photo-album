package com.dranoer.photoalbum.ui.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dranoer.photoalbum.domain.PhotoRepository
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

    private val _photoState = MutableStateFlow<PhotoUiState>(PhotoUiState.Loading())
    val photoState: StateFlow<PhotoUiState> = _photoState.asStateFlow()

    fun fetchPhotos(albumId: Int) {
        viewModelScope.launch {
            _photoState.value = PhotoUiState.Loading()
            try {
                val result = repository.fetchPhotos(id = albumId)
                _photoState.value = PhotoUiState.Success(data = result, isRefreshing = false)
            } catch (e: Exception) {
                _photoState.value = PhotoUiState.Error(message = e.message ?: "Unknown error")
            }
        }
    }
}