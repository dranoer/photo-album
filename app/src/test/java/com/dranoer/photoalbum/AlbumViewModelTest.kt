package com.dranoer.photoalbum

import com.dranoer.photoalbum.domain.PhotoRepository
import com.dranoer.photoalbum.domain.model.AlbumItem
import com.dranoer.photoalbum.ui.model.AlbumUiState
import com.dranoer.photoalbum.ui.album.AlbumViewModel
import com.dranoer.photoalbum.util.UiModelMapper
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
class AlbumViewModelTest {

    private lateinit var viewModel: AlbumViewModel
    private lateinit var repository: PhotoRepository
    private lateinit var mapper: UiModelMapper
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun set() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = AlbumViewModel(repository = repository, mapper = mapper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

//    @Test
//    fun `WHEN album data retrieved successfully THEN fetchAlbums updates AlbumUiState to Loaded`() =
//        runBlocking {
//            // GIVEN
//            val mockAlbums: List<AlbumItem> = listOf(mockk())
//            coEvery { repository.fetchAlbums() } returns mockAlbums
//
//            // WHEN
//            viewModel.fetchAlbums()
//
//            // THEN
//            delay(1000)
//            val expectedState = AlbumUiState.Loaded(data = mockAlbums)
//            val actualState = viewModel.albumState.value as AlbumUiState.Loaded
//            assertEquals(expectedState.data, actualState.data)
//        }

    @Test
    fun `WHEN NetworkException is thrown THEN fetchAlbums updates AlbumUiState to Error with correct message`() =
        runBlocking {
            // GIVEN
            val exceptionMessage = "Network error"
            coEvery { repository.fetchAlbums() } throws AppException.NetworkException(exceptionMessage)

            // WHEN
            viewModel.fetchAlbums()

            // THEN
            delay(1000)
            val expectedState = AlbumUiState.Error(message = exceptionMessage)
            val actualState = viewModel.albumState.value as AlbumUiState.Error
            assertEquals(expectedState.message, actualState.message)
        }

    @Test
    fun `WHEN DataNotFoundException is thrown THEN fetchAlbums updates AlbumUiState to Error with correct message`() =
        runBlocking {
            // GIVEN
            val exceptionMessage = "Data not found"
            coEvery { repository.fetchAlbums() } throws AppException.DataNotFoundException(exceptionMessage)

            // WHEN
            viewModel.fetchAlbums()

            // THEN
            delay(1000)
            val expectedState = AlbumUiState.Error(message = exceptionMessage)
            val actualState = viewModel.albumState.value as AlbumUiState.Error
            assertEquals(expectedState.message, actualState.message)
        }

    @Test
    fun `WHEN ViewModel is initialized THEN AlbumUiState is Empty`() =
        runBlocking {
            // WHEN
            val actualState = viewModel.albumState.value

            // THEN
            assertEquals(AlbumUiState.Empty, actualState)
        }
}