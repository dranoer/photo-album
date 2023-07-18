package com.dranoer.photoalbum

import com.dranoer.photoalbum.domain.PhotoRepository
import com.dranoer.photoalbum.domain.model.PhotoItem
import com.dranoer.photoalbum.ui.photo.PhotoUiState
import com.dranoer.photoalbum.ui.photo.PhotoViewModel
import com.dranoer.photoalbum.util.exception.AppException
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PhotoViewModelTest {

    private lateinit var viewModel: PhotoViewModel
    private lateinit var repository: PhotoRepository
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun set() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = PhotoViewModel(repository = repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN photo data retrieved successfully THEN fetchPhotos updates PhotoUiState to Loaded`() =
        runBlocking {
            // GIVE
            val albumId = 1
            val photos = listOf<PhotoItem>(
                PhotoItem(
                    albumId = 1,
                    id = 2,
                    title = "This is a normal title.",
                    url = "",
                    thumbnailUrl = ""
                ),
                PhotoItem(
                    albumId = 3,
                    id = 4,
                    title = "This is a normal title.",
                    url = "",
                    thumbnailUrl = ""
                )
            )
            coEvery { repository.fetchPhotos(albumId) } returns photos

            // WHEN
            viewModel.fetchPhotos(albumId = albumId)

            // THEN
            delay(1000)
            val expectedState = PhotoUiState.Loaded(data = photos)
            val actualState = viewModel.photoState.value as PhotoUiState.Loaded
            assertEquals(expectedState.data, actualState.data)
        }

    @Test
    fun `WHEN NetworkException is thrown THEN fetchPhotos updates PhotoUiState to Error with correct message`() =
        runBlocking {
            // GIVEN
            val albumId = 1
            val exceptionMessage = "Network error"
            coEvery { repository.fetchPhotos(albumId) } throws AppException.NetworkException(exceptionMessage)

            // WHEN
            viewModel.fetchPhotos(albumId = albumId)

            // THEN
            delay(1000)
            val expectedState = PhotoUiState.Error(message = exceptionMessage)
            val actualState = viewModel.photoState.value as PhotoUiState.Error
            assertEquals(expectedState.message, actualState.message)
        }

    @Test
    fun `WHEN DataNotFoundException is thrown THEN fetchPhotos updates PhotoUiState to Error with correct message`() =
        runBlocking {
            // GIVEN
            val albumId = 1
            val exceptionMessage = "Data not found"
            coEvery { repository.fetchPhotos(albumId) } throws AppException.DataNotFoundException(exceptionMessage)

            // WHEN
            viewModel.fetchPhotos(albumId = albumId)

            // THEN
            delay(1000)
            val expectedState = PhotoUiState.Error(message = exceptionMessage)
            val actualState = viewModel.photoState.value as PhotoUiState.Error
            assertEquals(expectedState.message, actualState.message)
        }

    @Test
    fun `WHEN ViewModel is initialized THEN PhotoUiState is Empty`() =
        runBlocking {
            // WHEN
            val actualState = viewModel.photoState.value

            // THEN
            assertEquals(PhotoUiState.Empty, actualState)
        }
}