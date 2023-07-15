package com.dranoer.photoalbum.ui.album

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
class AlbumViewModel @Inject constructor(
    private val repository: PhotoRepository,
) : ViewModel() {

    private val _albumState = MutableStateFlow<AlbumUiState>(AlbumUiState.Loading())
    val albumState: StateFlow<AlbumUiState> = _albumState.asStateFlow()

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            _albumState.value = AlbumUiState.Loading()

            try {
                val response = repository.fetchAlbums()
                _albumState.value = AlbumUiState.Success(data = response)

            } catch (e: Exception) {
                _albumState.value = AlbumUiState.Error(message = e.message ?: "Unknown error")
            }
        }
    }
}