package com.dranoer.photoalbum.ui.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dranoer.photoalbum.domain.PhotoRepository
import com.dranoer.photoalbum.ui.model.PhotoUiModel
import com.dranoer.photoalbum.ui.model.PhotoUiState
import com.dranoer.photoalbum.util.UiModelMapper
import com.dranoer.photoalbum.util.exception.AppException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val repository: PhotoRepository,
    private val mapper: UiModelMapper,
) : ViewModel() {

    private val _photoState = MutableStateFlow<PhotoUiState>(PhotoUiState.Empty)
    val photoState: StateFlow<PhotoUiState> = _photoState.asStateFlow()

    fun fetchPhotos(albumId: Int) {
        _photoState.value = PhotoUiState.Loading
        viewModelScope.launch {
            try {
                val result = repository.fetchPhotos(id = albumId)
                val uiModel = mapper.mapPhotos(photoList = result)
                _photoState.value = PhotoUiState.Loaded(data = uiModel, isRefreshing = false)
            } catch (e: AppException) {
                when (e) {
                    is AppException.NetworkException -> {
                        _photoState.value = PhotoUiState.Error(message = e.message ?: "Network error")
                    }

                    is AppException.DataNotFoundException -> {
                        _photoState.value = PhotoUiState.Error(message = e.message ?: "Data not found")
                    }
                }
            }
        }
    }

    fun getPhotoDetail(photoId: Int): PhotoUiModel? {
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