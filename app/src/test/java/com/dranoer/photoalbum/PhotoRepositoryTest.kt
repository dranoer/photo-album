package com.dranoer.photoalbum

import com.dranoer.photoalbum.data.remote.WebService
import com.dranoer.photoalbum.data.remote.model.AlbumModel
import com.dranoer.photoalbum.data.remote.model.PhotoModel
import com.dranoer.photoalbum.domain.PhotoMapper
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
    private lateinit var mapper: PhotoMapper
    private lateinit var repository: PhotoRepository
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        webService = mockk()
        mapper = mockk(relaxed = true)
        repository = PhotoRepository(webService = webService, mapper = mapper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN album data is retrieved successfully THEN fetchAlbums returns a list of AlbumItem`() =
        runBlocking {
            // GIVEN
            val mockAlbumModel = listOf(
                AlbumModel(
                    userId = 1,
                    id = 2,
                    title = "This is a title"
                )
            )
            val mockAlbumItem = listOf(
                AlbumItem(
                    userId = 1,
                    id = 2,
                    title = "This is a title"
                )
            )
            coEvery { webService.fetchAlbums() } returns mockAlbumModel
            coEvery { mapper.mapAlbums(mockAlbumModel) } returns mockAlbumItem

            // WHEN
            val result = repository.fetchAlbums()

            // THEN
            assertEquals(mockAlbumItem, result)
            coVerify { mapper.mapAlbums(mockAlbumModel) }
        }

    @Test
    fun `WHEN photo data is retrieved successfully THEN fetchPhotos returns a list of PhotoItem`() =
        runBlocking {
            // GIVEN
            val albumId = 1
            val mockPhotoModel = listOf(
                PhotoModel(
                    albumId = 1,
                    id = 2,
                    title = "This is a normal title.",
                    url = "",
                    thumbnailUrl = ""
                ),
            )
            val mockPhotoItem = listOf(
                PhotoItem(
                    albumId = 1,
                    id = 2,
                    title = "This is a normal title.",
                    url = "",
                    thumbnailUrl = ""
                ),
            )
            coEvery { webService.fetchPhotos(id = albumId) } returns mockPhotoModel
            coEvery { mapper.mapPhotos(mockPhotoModel) } returns mockPhotoItem

            // WHEN
            val result = repository.fetchPhotos(albumId)

            // THEN
            assertEquals(mockPhotoItem, result)
            coVerify { mapper.mapPhotos(mockPhotoModel) }
        }
}