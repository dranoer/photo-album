package com.dranoer.photoalbum

import com.dranoer.photoalbum.data.remote.WebService
import com.dranoer.photoalbum.data.remote.model.AlbumModel
import com.dranoer.photoalbum.data.remote.model.PhotoModel
import com.dranoer.photoalbum.domain.DomainModelMapper
import com.dranoer.photoalbum.domain.PhotoRepository
import com.dranoer.photoalbum.domain.model.AlbumItem
import com.dranoer.photoalbum.domain.model.PhotoItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PhotoRepositoryTest {

    private lateinit var webService: WebService
    private lateinit var domainMapper: DomainModelMapper
    private lateinit var repository: PhotoRepository
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        webService = mockk()
        domainMapper = mockk(relaxed = true)
        repository = PhotoRepository(webService = webService, domainMapper = domainMapper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN album data is retrieved successfully THEN fetchAlbums returns a list of AlbumItem`() =
        runBlocking {
            // GIVEN
            val mockAlbumModel: List<AlbumModel> = listOf(mockk())
            val mockAlbumItem: List<AlbumItem> = listOf(mockk())
            coEvery { webService.fetchAlbums() } returns mockAlbumModel
            coEvery { domainMapper.mapAlbums(mockAlbumModel) } returns mockAlbumItem

            // WHEN
            val result = repository.fetchAlbums()

            // THEN
            assertEquals(mockAlbumItem, result)
            coVerify { domainMapper.mapAlbums(mockAlbumModel) }
        }

    @Test
    fun `WHEN photo data is retrieved successfully THEN fetchPhotos returns a list of PhotoItem`() =
        runBlocking {
            // GIVEN
            val albumId = 1
            val mockPhotoModel: List<PhotoModel> = listOf(mockk())
            val mockPhotoItem: List<PhotoItem> = listOf(mockk())
            coEvery { webService.fetchPhotos(id = albumId) } returns mockPhotoModel
            coEvery { domainMapper.mapPhotos(mockPhotoModel) } returns mockPhotoItem

            // WHEN
            val result = repository.fetchPhotos(albumId)

            // THEN
            assertEquals(mockPhotoItem, result)
            coVerify { domainMapper.mapPhotos(mockPhotoModel) }
        }

    @Test
    fun `WHEN an exception is thrown during album data retrieval THEN fetchAlbums throws an AppException`() =
        runBlocking {
            // GIVEN
            val exceptionMessage = "Network error"
            coEvery { webService.fetchAlbums() } throws Exception(exceptionMessage)

            // WHEN
            val result = runCatching { repository.fetchAlbums() }

            // THEN
            assert(result.isFailure)
            assertEquals(exceptionMessage, result.exceptionOrNull()?.message)
        }

    @Test
    fun `WHEN an exception is thrown during photo data retrieval THEN fetchPhotos throws an AppException`() =
        runBlocking {
            // GIVEN
            val albumId = 1
            val exceptionMessage = "Failed to retrieve data"
            coEvery { webService.fetchPhotos(id = albumId) } throws Exception(exceptionMessage)

            // WHEN
            val result = runCatching { repository.fetchPhotos(id = albumId) }

            // THEN
            assert(result.isFailure)
            assertEquals(exceptionMessage, result.exceptionOrNull()?.message)
        }
}