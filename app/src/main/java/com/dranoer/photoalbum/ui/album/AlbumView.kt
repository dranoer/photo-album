package com.dranoer.photoalbum.ui.album

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.dranoer.photoalbum.R
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme
import com.dranoer.photoalbum.util.getRandomColor

@Composable
fun AlbumCard(
    modifier: Modifier,
    id: String,
    title: String,
    color: Color,
    onAlbumClicked: (String) -> Unit,
) {
    Surface(
        shape = MaterialTheme.shapes.extraSmall,
        color = color,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onAlbumClicked(id) },
    ) {
        Text(
            text = title,
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.black),
            modifier = modifier.padding(dimensionResource(id = R.dimen.size_14)),
        )
    }
}

//region Preview
@Preview
@Composable
private fun AlbumCardPreview_Normal() {
    PhotoAlbumTheme {
        AlbumCard(
            id = "2",
            title = "AlbumItem title",
            color = getRandomColor(),
            onAlbumClicked = {},
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
private fun AlbumCardPreview_LongTitle() {
    PhotoAlbumTheme {
        AlbumCard(
            id = "2",
            title = "This is a very long long long long long long long long long long long long long long long AlbumItem title.",
            color = getRandomColor(),
            onAlbumClicked = {},
            modifier = Modifier,
        )
    }
}
//endregion