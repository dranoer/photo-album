package com.dranoer.photoalbum

import com.dranoer.photoalbum.data.remote.model.AlbumModel
import com.dranoer.photoalbum.data.remote.model.PhotoModel
import com.dranoer.photoalbum.domain.DomainModelMapper
import com.dranoer.photoalbum.domain.model.AlbumItem
import com.dranoer.photoalbum.domain.model.PhotoItem
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DomainModelMapperTest {
    private lateinit var domainMapper: DomainModelMapper

    @Before
    fun setUp() {
        domainMapper = DomainModelMapper()
    }

    @Test
    fun `WHEN mapAlbums is called THEN it returns a list of AlbumItem`() {
        // GIVEN
        val albumModel =
            AlbumModel(
                userId = 1,
                id = 2,
                title = "Album title"
            )
        val expectedAlbumItem =
            AlbumItem(
                userId = 1,
                id = 2,
                title = "Album title"
            )

        // WHEN
        val result = domainMapper.mapAlbums(listOf(albumModel))

        // THEN
        assertEquals(listOf(expectedAlbumItem), result)
    }

    @Test
    fun `WHEN mapPhotos is called THEN it returns a list of PhotoItem`() {
        // GIVEN
        val photoModel =
            PhotoModel(
                albumId = 1,
                id = 2,
                title = "Photo title",
                url = "url",
                thumbnailUrl = "thumbnailUrl"
            )
        val expectedPhotoItem =
            PhotoItem(
                albumId = 1,
                id = 2,
                title = "Photo title",
                url = "url",
                thumbnailUrl = "thumbnailUrl"
            )

        // WHEN
        val result = domainMapper.mapPhotos(listOf(photoModel))

        // THEN
        assertEquals(listOf(expectedPhotoItem), result)
    }
}