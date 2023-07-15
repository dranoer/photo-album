package com.dranoer.photoalbum.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme

@Composable
fun AlbumCard(modifier: Modifier, id: String, title: String, onAlbumClicked: (String) -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Yellow)
            .clickable { onAlbumClicked(id) },
        shape = RoundedCornerShape(6)
    ) {
        Text(modifier = modifier.padding(10.dp), text = title)
    }
}

//region Preview
@Preview(name = "Normal")
@Composable
private fun AlbumCardPreview() {
    PhotoAlbumTheme {
        AlbumCard(modifier = Modifier, id = "2", title = "Album title", onAlbumClicked = {})
    }
}
//endregion