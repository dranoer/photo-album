package com.dranoer.photoalbum

import com.dranoer.photoalbum.domain.PhotoRepository
import com.dranoer.photoalbum.domain.model.PhotoItem
import com.dranoer.photoalbum.ui.photo.PhotoUiState
import com.dranoer.photoalbum.ui.photo.PhotoViewModel
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
    fun `WHEN there is an error retrieving photo data THEN fetchPhotos updates PhotoUiState to Error`() =
        runBlocking {
            // GIVEN
            val albumId = 1
            val exceptionMessage = "Failed to retrieve data"
            coEvery { repository.fetchPhotos(id = albumId) } throws Exception(exceptionMessage)

            // WHEN
            viewModel.fetchPhotos(albumId = albumId)

            // THEN
            delay(1000)
            val expectedState = PhotoUiState.Error(message = exceptionMessage)
            val actualState = viewModel.photoState.value as PhotoUiState.Error
            assertEquals(expectedState.message, actualState.message)
        }
}