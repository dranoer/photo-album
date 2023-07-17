package com.dranoer.photoalbum.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.dranoer.photoalbum.R
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme

@Composable
fun AlbumCard(modifier: Modifier, id: String, title: String, onAlbumClicked: (String) -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onAlbumClicked(id) },
        shape = RoundedCornerShape(10),
        colors = CardDefaults.cardColors(
            containerColor = when (isSystemInDarkTheme()) {
                true -> colorResource(id = R.color.gray_400)
                false -> colorResource(id = R.color.white)
            }
        ),
    ) {
        Text(
            modifier = modifier.padding(dimensionResource(id = R.dimen.size_14)),
            text = title,
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium,
            color = when (isSystemInDarkTheme()) {
                true -> colorResource(id = R.color.white)
                false -> colorResource(id = R.color.black)
            },
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
            title = "Album title",
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
            title = "This is a very very very very very very very very very very very very very very very very long title.",
            onAlbumClicked = {},
        )
    }
}
//endregion