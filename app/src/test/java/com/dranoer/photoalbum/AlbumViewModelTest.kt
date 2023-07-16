package com.dranoer.photoalbum

import com.dranoer.photoalbum.domain.PhotoRepository
import com.dranoer.photoalbum.domain.model.AlbumItem
import com.dranoer.photoalbum.ui.album.AlbumUiState
import com.dranoer.photoalbum.ui.album.AlbumViewModel
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
class AlbumViewModelTest {

    private lateinit var viewModel: AlbumViewModel
    private lateinit var repository: PhotoRepository
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun set() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = AlbumViewModel(repository = repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN album data retrieved successfully THEN fetchAlbums updates AlbumUiState to Loaded`() =
        runBlocking {
            // GIVE
            val albums = listOf<AlbumItem>(
                AlbumItem(
                    userId = 1,
                    id = 2,
                    title = "This is a title"
                )
            )
            coEvery { repository.fetchAlbums() } returns albums

            // WHEN
            viewModel.fetchAlbums()

            // THEN
            delay(1000)
            val expectedState = AlbumUiState.Loaded(data = albums)
            val actualState = viewModel.albumState.value as AlbumUiState.Loaded
            assertEquals(expectedState.data, actualState.data)
        }

    @Test
    fun `WHEN there is an error retrieving album data THEN fetchAlbums updates AlbumUiState to Error`() =
        runBlocking {
            // GIVEN
            val exceptionMessage = "Failed to retrieve data"
            coEvery { repository.fetchAlbums() } throws Exception(exceptionMessage)

            // WHEN
            viewModel.fetchAlbums()

            // THEN
            delay(1000)
            val expectedState = AlbumUiState.Error(message = exceptionMessage)
            val actualState = viewModel.albumState.value as AlbumUiState.Error
            assertEquals(expectedState.message, actualState.message)
        }
}