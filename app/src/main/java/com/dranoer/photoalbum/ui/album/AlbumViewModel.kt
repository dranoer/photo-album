package com.dranoer.photoalbum.ui.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dranoer.photoalbum.domain.PhotoRepository
import com.dranoer.photoalbum.util.exception.AppException
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

    private val _albumState = MutableStateFlow<AlbumUiState>(AlbumUiState.Empty)
    val albumState: StateFlow<AlbumUiState> = _albumState.asStateFlow()

    init {
        fetchAlbums()
    }

    fun fetchAlbums() {
        _albumState.value = AlbumUiState.Loading
        viewModelScope.launch {
            try {
                val response = repository.fetchAlbums()
                _albumState.value = AlbumUiState.Loaded(data = response)

            } catch (e: AppException) {
                when (e) {
                    is AppException.NetworkException -> {
                        _albumState.value = AlbumUiState.Error(message = e.message ?: "Network error")
                    }

                    is AppException.DataNotFoundException -> {
                        _albumState.value = AlbumUiState.Error(message = e.message ?: "Data not found")
                    }
                }
            }
        }
    }
}