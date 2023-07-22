package com.dranoer.photoalbum.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onAlbumClicked(id) },
        shape = RoundedCornerShape(10),
        colors = CardDefaults.cardColors(
            containerColor = color,
        ),
    ) {
        Text(
            modifier = modifier.padding(dimensionResource(id = R.dimen.size_14)),
            text = title,
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.black),
        )
    }
}

//region Preview
@Preview
@Composable
private fun AlbumCardPreview_Normal() {
    PhotoAlbumTheme {
        AlbumCard(
            modifier = Modifier,
            id = "2",
            title = "AlbumItem title",
            color = getRandomColor(),
            onAlbumClicked = {},
        )
    }
}

@Preview
@Composable
private fun AlbumCardPreview_LongTitle() {
    PhotoAlbumTheme {
        AlbumCard(
            modifier = Modifier,
            id = "2",
            title = "This is a very long long long long long long long long long long long long long long long AlbumItem title.",
            color = getRandomColor(),
            onAlbumClicked = {},
        )
    }
}

@Preview
@Composable
private fun AlbumCardPreview_NoTitle() {
    PhotoAlbumTheme {
        AlbumCard(
            modifier = Modifier,
            id = "2",
            title = "",
            color = getRandomColor(),
            onAlbumClicked = {},
        )
    }
}

@Preview
@Composable
private fun AlbumCardPreview_DarkTheme() {
    PhotoAlbumTheme(darkTheme = true) {
        AlbumCard(
            modifier = Modifier,
            id = "2",
            title = "AlbumItem title.",
            color = getRandomColor(),
            onAlbumClicked = {},
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
private fun AlbumCardPreview_Clickable() {
    PhotoAlbumTheme {
        AlbumCard(
            modifier = Modifier,
            id = "2",
            title = "AlbumItem title.",
            color = getRandomColor(),
            onAlbumClicked = { clickedId -> println("Clicked on album with id: $clickedId") },
        )
    }
}
//endregion