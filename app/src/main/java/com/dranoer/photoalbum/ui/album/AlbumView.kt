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
fun AlbumView(
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
private fun AlbumViewPreview_Normal() {
    PhotoAlbumTheme {
        AlbumView(
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
private fun AlbumViewPreview_LongTitle() {
    PhotoAlbumTheme {
        AlbumView(
            id = "2",
            title = "This is a very long long long long long long long long long long long long long long long AlbumItem title.",
            color = getRandomColor(),
            onAlbumClicked = {},
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
private fun AlbumViewPreview_NoTitle() {
    PhotoAlbumTheme {
        AlbumView(
            id = "2",
            title = "",
            color = getRandomColor(),
            onAlbumClicked = {},
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
private fun AlbumViewPreview_DarkTheme() {
    PhotoAlbumTheme(darkTheme = true) {
        AlbumView(
            id = "2",
            title = "AlbumItem title.",
            color = getRandomColor(),
            onAlbumClicked = {},
            modifier = Modifier,
        )
    }
}

/**
 * This function depicts the `AlbumCard` composable in a state where it's clickable.
 * This is to visualize the UI state in the Compose Preview,
 * although actual click events are not interactive within the Preview.
 */
@Preview
@Composable
private fun AlbumViewPreview_Clickable() {
    PhotoAlbumTheme {
        AlbumView(
            id = "2",
            title = "AlbumItem title.",
            color = getRandomColor(),
            onAlbumClicked = { clickedId -> println("Clicked on album with id: $clickedId") },
            modifier = Modifier,
        )
    }
}
//endregion